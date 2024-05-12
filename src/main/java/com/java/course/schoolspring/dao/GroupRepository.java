package com.java.course.schoolspring.dao;

import com.java.course.schoolspring.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("SELECT g FROM Group g WHERE SIZE(g.students) <= :maxStudentCount")
    List<Group> findByStudentsLessThanEqual(@Param("maxStudentCount") int maxStudentCount);
}
