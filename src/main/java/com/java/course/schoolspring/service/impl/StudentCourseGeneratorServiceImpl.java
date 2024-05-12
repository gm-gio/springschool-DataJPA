package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseRepository;
import com.java.course.schoolspring.dao.StudentRepository;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.CourseService;
import com.java.course.schoolspring.service.StudentsCoursesGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class StudentCourseGeneratorServiceImpl implements StudentsCoursesGeneratorService {


    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    private CourseService courseService;

    public StudentCourseGeneratorServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void assignCoursesToStudents() {

        List<Student> students = studentRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        for (Student student : students) {
            int numCourses = (int) (Math.random() * 3) + 1;
            Collections.shuffle(courses);
            for (int i = 0; i < numCourses && i < courses.size(); i++) {
                Course course = courses.get(i);
                courseService.addStudentToCourse(student.getStudentId(), course.getCourseId());
            }
        }
    }
}

