package com.erp.school.result;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAdmissionId; // Student admission ID
    private String dateOfBirth; // Date of birth in YYYY-MM-DD format
    private Long classId; // Class ID
    private String semester; // Semester or term

    @ElementCollection
    private Map<String, String> subjectMarks; // Subject -> "Marks/Total" (e.g., "85/100")
}