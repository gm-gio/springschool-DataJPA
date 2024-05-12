package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.CourseRepository;
import com.java.course.schoolspring.dao.StudentRepository;
import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import com.java.course.schoolspring.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> findStudentByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(null);
        Course course = courseRepository.findById(courseId).orElseThrow(null);

        if (student != null && course != null) {
            course.addStudent(student);
        }

    }

    @Override
    public void deleteStudentFromCourseService(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(null);
        Course course = courseRepository.findById(courseId).orElseThrow(null);

        if (student != null && course != null) {
            course.removeStudent(student);
        }
    }

    @Override
    public void createNewCourse(Course course) {
        courseRepository.save(course);
    }

}
