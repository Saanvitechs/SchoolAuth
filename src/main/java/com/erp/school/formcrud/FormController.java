package com.erp.school.formcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form, @RequestHeader("tenant") String tenant) {
        // Use tenant if needed for processing
        return ResponseEntity.ok(formService.createForm(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        // Use tenant if needed for processing
        return ResponseEntity.ok(formService.getFormById(id));
    }

    @GetMapping
    public ResponseEntity<List<Form>> getAllForms(@RequestHeader("tenant") String tenant) {
        // Use tenant if needed for processing
        return ResponseEntity.ok(formService.getAllForms());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable Long id, @RequestBody Form form, @RequestHeader("tenant") String tenant) {
        // Use tenant if needed for processing
        return ResponseEntity.ok(formService.updateForm(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        // Use tenant if needed for processing
        formService.deleteForm(id);
        return ResponseEntity.noContent().build();
    }
}