package com.erp.school.service;

import com.erp.school.model.Teacher;
import com.erp.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    PasswordEncoder encoder;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher employee) {

        // Check if the employee already exists
        if (employee.getId() != null && teacherRepository.existsById(employee.getId())) {
            // Fetch the existing employee to retain the current password
            Teacher existingEmployee = teacherRepository.findById(employee.getId()).orElseThrow(() -> new RuntimeException("Employee not found"));
            if (employee.getPassword().equals(existingEmployee.getPassword())) {
                employee.setPassword(existingEmployee.getPassword());
            } else {
                // Encode the password for new employee
                employee.setPassword(encoder.encode(employee.getPassword()));
            }
        }else {
            // Encode the password for new employee
            employee.setPassword(encoder.encode(employee.getPassword()));
        }

        employee = teacherRepository.save(employee);

        return employee;
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}