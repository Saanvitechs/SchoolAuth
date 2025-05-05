package com.erp.school.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public ResponseEntity<Result> getResultByStudentDetails(
            @RequestParam Long studentAdmissionId,
            @RequestParam String dateOfBirth,
            @RequestParam Long classId,
            @RequestHeader("tenant") String tenant) {
        Optional<Result> result = resultService.getResultByStudentDetails(studentAdmissionId, dateOfBirth, classId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Result> saveResult(
            @RequestBody Result result,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(resultService.saveResult(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(
            @PathVariable Long id,
            @RequestHeader("tenant") String tenant) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}