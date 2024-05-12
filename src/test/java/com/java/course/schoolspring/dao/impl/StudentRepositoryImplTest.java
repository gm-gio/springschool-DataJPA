package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.StudentRepository;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.impl.StudentServiceImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        NamedParameterJdbcTemplate.class,
        StudentServiceImpl.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_data.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class StudentRepositoryImplTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    EntityManager em;

    @Test
    void shouldFindAllStudents() {
        List<Student> students = studentRepository.findAll();
        assertEquals(10, students.size());
    }

    @Test
    void shouldFindStudentById() {
        Optional<Student> optionalStudent = studentRepository.findById(10001);
        assertTrue(optionalStudent.isPresent());
        assertEquals("Mia", optionalStudent.get().getFirstName());

    }

    @Test
    void shouldAddNewStudent() {
        Student student = new Student();
        student.setFirstName("Gm");
        student.setLastName("Mg");
        Student addedStudent = studentRepository.save(student);
        assertEquals("Gm", addedStudent.getFirstName());
        assertEquals("Mg", addedStudent.getLastName());

    }

    @Test
    void shouldUpdateStudent() {
        Student updatedStudent = studentRepository.findById(10001).orElseThrow();
        updatedStudent.setFirstName("Gm");
        updatedStudent.setLastName("Mg");
        studentRepository.save(updatedStudent);
        assertEquals("Gm", updatedStudent.getFirstName());
        assertEquals("Mg", updatedStudent.getLastName());
    }

    @Test
    void shouldDeleteStudentById() {
        studentRepository.deleteById(10001);
        Optional<Student> deletedStudent = studentRepository.findById(10001);
        assertFalse(deletedStudent.isPresent());
    }
}
