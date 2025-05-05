package com.erp.school.studentattendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/student/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAttendanceByBatchAndDate(@RequestHeader("tenant") String tenant,
            @RequestParam String batch,
            @RequestParam String date) {
        return attendanceService.getAttendanceByBatchAndDate(batch, LocalDate.parse(date));
    }

    @PostMapping
    public ResponseEntity<List<Attendance>> markAttendance(@RequestBody List<Attendance> attendanceList,@RequestHeader("tenant") String tenant) {
        List<Attendance> savedAttendance = attendanceService.markAttendance(attendanceList);
        return ResponseEntity.ok(savedAttendance);
    }
}