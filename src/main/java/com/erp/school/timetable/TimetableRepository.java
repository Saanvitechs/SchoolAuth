package com.erp.school.timetable;



import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findByClassId(Long classId);
    List<Timetable> findByTeacherId(Long teacherId);
    List<Timetable> findByDayNumber(int dayNumber);
    List<Timetable> findByClassIdAndDayNumber(Long classId, int dayNumber);
    List<Timetable> findByTeacherIdAndDayNumber(Long teacherId, int dayNumber);
}