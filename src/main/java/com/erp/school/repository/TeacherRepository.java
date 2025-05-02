package com.erp.school.repository;

import com.erp.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String username);
}