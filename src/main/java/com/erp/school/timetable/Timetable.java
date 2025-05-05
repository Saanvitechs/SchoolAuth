package com.erp.school.timetable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayOfWeek; // e.g., Monday, Tuesday
    private int dayNumber; // Day 1 to Day 7
    private int periodNumber; // 1 to 9
    private String subject;
    private Long teacherId;
    private Long classId;
    private LocalTime startTime;
    private LocalTime endTime;


}