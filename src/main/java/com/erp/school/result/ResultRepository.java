package com.erp.school.result;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByStudentAdmissionIdAndDateOfBirthAndClassId(
            Long studentAdmissionId, String dateOfBirth, Long classId);
}