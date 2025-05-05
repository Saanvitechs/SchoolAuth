package com.erp.school.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Event>> getEventsByStatus(
            @PathVariable String status,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(eventService.getEventsByStatus(status));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Event>> getEventsByTeacher(
            @PathVariable Long teacherId,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(eventService.getEventsByTeacher(teacherId));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestBody Event event,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event updatedEvent,
            @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(eventService.updateEvent(id, updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id,
            @RequestHeader("tenant") String tenant) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}