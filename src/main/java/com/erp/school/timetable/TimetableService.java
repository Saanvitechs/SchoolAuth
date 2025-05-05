package com.erp.school.timetable;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    public List<Timetable> getTimetableForClass(Long classId) {
        return timetableRepository.findByClassId(classId);
    }

    public List<Timetable> getTimetableForTeacher(Long teacherId) {
        return timetableRepository.findByTeacherId(teacherId);
    }

    public List<Timetable> getTimetableForDay(int dayNumber) {
        return timetableRepository.findByDayNumber(dayNumber);
    }

    public List<Timetable> getTimetableForStudentByDay(Long classId, int dayNumber) {
        return timetableRepository.findByClassIdAndDayNumber(classId, dayNumber);
    }

    public List<Timetable> getTimetableForTeacherByDay(Long teacherId, int dayNumber) {
        return timetableRepository.findByTeacherIdAndDayNumber(teacherId, dayNumber);
    }

    public Timetable createTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }
}