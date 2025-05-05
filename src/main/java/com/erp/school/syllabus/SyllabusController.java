package com.erp.school.syllabus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;

    @GetMapping("/class/{classId}/semester/{semester}")
    public ResponseEntity<List<Syllabus>> getSyllabusByClassAndSemester(
            @PathVariable Long classId,
            @PathVariable int semester,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(syllabusService.getSyllabusByClassAndSemester(classId, semester));
    }

    @PostMapping
    public ResponseEntity<Syllabus> createSyllabus(
            @RequestBody Syllabus syllabus,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(syllabusService.createSyllabus(syllabus));
    }
}