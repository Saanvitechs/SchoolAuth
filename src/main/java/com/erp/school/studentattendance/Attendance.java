package com.erp.school.studentattendance;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long classTeacherId;
    private String batch;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    public enum AttendanceStatus {
        PRESENT, LATE, ABSENT, LEAVE, HOLIDAY
    }


}