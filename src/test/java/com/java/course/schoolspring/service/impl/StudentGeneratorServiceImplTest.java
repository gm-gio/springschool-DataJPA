package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupRepository;
import com.java.course.schoolspring.dao.StudentRepository;
import com.java.course.schoolspring.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {StudentGeneratorServiceImpl.class})
public class StudentGeneratorServiceImplTest {

    @MockBean
    StudentRepository studentRepository;
    @MockBean
    GroupRepository groupRepository;

    @Autowired
    StudentGeneratorServiceImpl studentGeneratorService;

    @Captor
    ArgumentCaptor<List<Student>> studentCaptor;


    @Test
    void generatedStudentsWhenNeed() {
        when(studentRepository.count()).thenReturn(0L);
        when(groupRepository.count()).thenReturn(0L);

        studentGeneratorService.generateStudentsIfNeed();

        verify(studentRepository, times(1)).saveAll(studentCaptor.capture());

        List<Student> capturedStudents = studentCaptor.getValue();

        assertEquals(200, capturedStudents.size());
    }


}
