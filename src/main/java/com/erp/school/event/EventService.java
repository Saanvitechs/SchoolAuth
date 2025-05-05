package com.erp.school.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }

    public List<Event> getEventsByTeacher(Long teacherId) {
        return eventRepository.findByCreatedByTeacherId(teacherId);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setHeading(updatedEvent.getHeading());
            event.setSubject(updatedEvent.getSubject());
            event.setDescription(updatedEvent.getDescription());
            event.setStartDate(updatedEvent.getStartDate());
            event.setEndDate(updatedEvent.getEndDate());
            event.setStatus(updatedEvent.getStatus());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}