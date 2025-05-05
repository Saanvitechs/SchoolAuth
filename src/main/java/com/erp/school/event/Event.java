package com.erp.school.event;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heading; // Event heading
    private String subject; // Event subject
    private String description; // Additional details
    private LocalDate startDate; // Event start date
    private LocalDate endDate; // Event end date
    private Long createdByTeacherId; // Teacher ID who created the event
    private String status; // Status: Started, Completed, Upcoming

    // Getters and Setters
}