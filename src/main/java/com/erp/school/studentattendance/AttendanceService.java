package com.erp.school.studentattendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAttendanceByBatchAndDate(String batch, LocalDate date) {
        return attendanceRepository.findByBatchAndDate(batch, date);
    }

    public List<Attendance> markAttendance(List<Attendance> attendanceList) {
        return attendanceRepository.saveAll(attendanceList);
    }
}