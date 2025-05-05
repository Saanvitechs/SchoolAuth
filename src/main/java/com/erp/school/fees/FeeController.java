package com.erp.school.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @PostMapping("/add")
    public ResponseEntity<Fee> addFee(
            @RequestParam Long studentId,
            @RequestParam double admissionCharges,
            @RequestParam double totalFee,
            @RequestParam String paymentFrequency,
            @RequestParam int batchYear) {
        return ResponseEntity.ok(feeService.addFee(studentId, admissionCharges, totalFee, paymentFrequency));
    }

    @PostMapping("/pay/{feeId}")
    public ResponseEntity<Fee> makePayment(
            @PathVariable Long feeId,
            @RequestParam double amount,
            @RequestParam String paymentMode,
            @RequestParam String referenceNumber,
            @RequestParam String month) {
        return ResponseEntity.ok(feeService.makePayment(feeId, amount, paymentMode, referenceNumber, month));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fee>> getFeesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(feeService.getFeesByStudent(studentId));
    }
}