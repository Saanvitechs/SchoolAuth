package com.erp.school.studentcurd;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentAdmissionId;
    private String studentClassRollNo;
    private String admissionDate;
    private String name;
    private String email;
    private String password;
    private String address;
    private String mobile;
    private String parentRelation;
    private String currentClass;
    private String parentName;
    private String parentMobile;
    private String parentEmail;

    @Column(name = "tenant_id")
    private String tenantId;


}