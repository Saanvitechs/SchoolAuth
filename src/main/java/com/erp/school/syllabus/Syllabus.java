package com.erp.school.syllabus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long classId; // Class identifier
    private int semester; // Semester number
    private String subject; // Subject name
    private String syllabusContent; // Syllabus details

    // Getters and Setters
}