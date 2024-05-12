package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Course;
import com.java.course.schoolspring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.courseName = :courseName")
    List<Student> findByCourseName(@Param("courseName") String courseName);

    List<Course> findByStudentsStudentId(Integer studentId);

}
