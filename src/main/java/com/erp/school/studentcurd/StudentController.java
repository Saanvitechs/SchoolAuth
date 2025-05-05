package com.erp.school.studentcurd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(@RequestHeader("tenant") String tenant) {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student, @RequestHeader("tenant") String tenant) {
        student.setTenantId(tenant);
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}