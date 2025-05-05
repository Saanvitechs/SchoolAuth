package com.erp.school.fees;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private double admissionCharges;
    private double totalFee;
    private String paymentFrequency; // Monthly, Quarterly, Semi-Annually, Annually
    private double paidAmount;
    private double remainingAmount;
    private LocalDate paymentDate;
    private String paymentMode; // e.g., Cash, Card, Online
    private String referenceNumber; // Transaction reference number
    private int batchYear; // Session year (e.g., 2023 for March 2023 to March 2024)

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Boolean> monthlyPaymentStatus; // Tracks payment status for each month
}