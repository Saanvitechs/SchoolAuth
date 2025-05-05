package com.erp.school.library;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String subject;
    private LocalDate purchaseDate;
    private int totalCopies;
    private int availableCopies;

    // Getters and Setters
}