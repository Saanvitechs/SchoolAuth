package com.erp.school.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Optional<Result> getResultByStudentDetails(Long studentAdmissionId, String dateOfBirth, Long classId) {
        return resultRepository.findByStudentAdmissionIdAndDateOfBirthAndClassId(studentAdmissionId, dateOfBirth, classId);
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}