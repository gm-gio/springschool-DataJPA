package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
