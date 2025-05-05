package com.erp.school.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    public Fee addFee(Long studentId, double admissionCharges, double totalFee, String paymentFrequency) {
        Fee fee = new Fee();
        fee.setStudentId(studentId);
        fee.setAdmissionCharges(admissionCharges);
        fee.setTotalFee(totalFee);
        fee.setPaymentFrequency(paymentFrequency);
        fee.setPaidAmount(0);
        fee.setRemainingAmount(totalFee);

        // Calculate the batch year cycle (e.g., April 2025 to March 2026)
        LocalDate now = LocalDate.now();
        int startYear = now.getMonthValue() >= 4 ? now.getYear() : now.getYear() - 1;
        int endYear = startYear + 1;
        fee.setBatchYear(startYear); // Store the starting year of the cycle

        // Initialize monthlyPaymentStatus with all months set to false
        Map<String, Boolean> monthlyPaymentStatus = new HashMap<>();
        for (String month : new String[]{"April", "May", "June", "July", "August", "September",
                "October", "November", "December", "January", "February", "March"}) {
            monthlyPaymentStatus.put(month, false);
        }
        fee.setMonthlyPaymentStatus(monthlyPaymentStatus);

        return feeRepository.save(fee);
    }

    public Fee makePayment(Long feeId, double amount, String paymentMode, String referenceNumber, String month) {
        Fee fee = feeRepository.findById(feeId).orElseThrow(() -> new RuntimeException("Fee record not found"));
        fee.setPaidAmount(fee.getPaidAmount() + amount);
        fee.setRemainingAmount(fee.getTotalFee() - fee.getPaidAmount());
        fee.setPaymentDate(LocalDate.now());
        fee.setPaymentMode(paymentMode);
        fee.setReferenceNumber(referenceNumber);

        // Mark the specified month as paid
        Map<String, Boolean> paymentStatus = fee.getMonthlyPaymentStatus();
        paymentStatus.put(month, true);
        fee.setMonthlyPaymentStatus(paymentStatus);

        return feeRepository.save(fee);
    }

    public List<Fee> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }
}