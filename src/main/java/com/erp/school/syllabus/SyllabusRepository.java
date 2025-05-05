package com.erp.school.syllabus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    List<Syllabus> findByClassIdAndSemester(Long classId, int semester);
}