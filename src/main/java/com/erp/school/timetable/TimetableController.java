package com.erp.school.timetable;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Timetable>> getTimetableForClass(@PathVariable Long classId, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.getTimetableForClass(classId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Timetable>> getTimetableForTeacher(@PathVariable Long teacherId, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.getTimetableForTeacher(teacherId));
    }

    @GetMapping("/day/{dayNumber}")
    public ResponseEntity<List<Timetable>> getTimetableForDay(@PathVariable int dayNumber, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.getTimetableForDay(dayNumber));
    }

    @GetMapping("/student/{classId}/day/{dayNumber}")
    public ResponseEntity<List<Timetable>> getTimetableForStudentByDay(@PathVariable Long classId, @PathVariable int dayNumber, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.getTimetableForStudentByDay(classId, dayNumber));
    }

    @GetMapping("/teacher/{teacherId}/day/{dayNumber}")
    public ResponseEntity<List<Timetable>> getTimetableForTeacherByDay(@PathVariable Long teacherId, @PathVariable int dayNumber, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.getTimetableForTeacherByDay(teacherId, dayNumber));
    }

    @PostMapping
    public ResponseEntity<Timetable> createTimetable(@RequestBody Timetable timetable, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(timetableService.createTimetable(timetable));
    }
}