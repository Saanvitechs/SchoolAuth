package com.erp.school.controller;

import com.erp.school.model.Teacher;
import com.erp.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers(@RequestHeader("tenant") String tenant) {
        // You can use tenantId for filtering or logging purposes
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        // Use tenantId if needed
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher, @RequestHeader("tenant") String tenant) {
        teacher.setTenantId(tenant); // Set tenantId in the Teacher object
        return teacherService.saveTeacher(teacher);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id, @RequestHeader("tenant") String tenant) {
        // Use tenantId if needed for validation or logging
        if (teacherService.getTeacherById(id).isPresent()) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}