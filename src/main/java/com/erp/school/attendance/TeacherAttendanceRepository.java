package com.erp.school.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {
    List<TeacherAttendance> findByTeacherIdAndDateBetween(Long teacherId, LocalDate startDate, LocalDate endDate);
}