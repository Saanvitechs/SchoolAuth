package com.erp.school.library;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Long studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private String instruction; // Instruction for the student
    // Getters and Setters
}