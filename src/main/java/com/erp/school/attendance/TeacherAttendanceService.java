package com.erp.school.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherAttendanceService {

    @Autowired
    private TeacherAttendanceRepository attendanceRepository;

    public TeacherAttendance markAttendance(Long teacherId, LocalDate date, String status) {
        if (status.equalsIgnoreCase("Holiday") && date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            status = "Present"; // Mark Sunday as present if it's a holiday
        }

        TeacherAttendance attendance = new TeacherAttendance();
        attendance.setTeacherId(teacherId);
        attendance.setDate(date);
        attendance.setStatus(status);
        return attendanceRepository.save(attendance);
    }

    public MonthlyAttendanceSummary calculateMonthlyAttendance(Long teacherId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<TeacherAttendance> attendances = attendanceRepository.findByTeacherIdAndDateBetween(teacherId, startDate, endDate);

        int presentDays = 0;
        int absentDays = 0;
        int leaveDays = 0;
        int holidays = 0;

        for (TeacherAttendance attendance : attendances) {
            switch (attendance.getStatus()) {
                case "Present" -> presentDays++;
                case "Absent" -> absentDays++;
                case "Leave" -> leaveDays++;
                case "Holiday" -> holidays++;
            }
        }

        return new MonthlyAttendanceSummary(presentDays, absentDays, leaveDays, holidays);
    }
}