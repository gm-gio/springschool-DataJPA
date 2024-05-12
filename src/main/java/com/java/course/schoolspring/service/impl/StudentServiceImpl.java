package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.StudentRepository;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addNewStudentService(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentByIdService(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
