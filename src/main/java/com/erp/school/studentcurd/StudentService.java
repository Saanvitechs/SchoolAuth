package com.erp.school.studentcurd;


import com.erp.school.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student employee) {
        // Check if the employee already exists
        if (employee.getId() != null && studentRepository.existsById(employee.getId())) {
            // Fetch the existing employee to retain the current password
            Student existingEmployee = studentRepository.findById(employee.getId()).orElseThrow(() -> new RuntimeException("Employee not found"));
            if (employee.getPassword().equals(existingEmployee.getPassword())) {
                employee.setPassword(existingEmployee.getPassword());
            } else {
                // Encode the password for new employee
                employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            }
        }else {
            // Encode the password for new employee
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }

        employee = studentRepository.save(employee);

        return employee;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}