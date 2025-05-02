package com.erp.school.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role; // e.g., admin, user
    private String position;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String address;
    private String password;
    private String tenantId; // ID of the tenant this teacher belongs to


}