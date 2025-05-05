package com.erp.school.syllabus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    public List<Syllabus> getSyllabusByClassAndSemester(Long classId, int semester) {
        return syllabusRepository.findByClassIdAndSemester(classId, semester);
    }

    public Syllabus createSyllabus(Syllabus syllabus) {
        return syllabusRepository.save(syllabus);
    }
}