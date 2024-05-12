package com.java.course.schoolspring.dao.impl;

import com.java.course.schoolspring.dao.GroupRepository;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.service.GroupService;
import com.java.course.schoolspring.service.impl.GroupServiceImpl;
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
        GroupServiceImpl.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_data.sql", "/sql/sample_data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class GroupRepositoryImplTest {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    EntityManager em;

    @Test
    void shouldFindAllGroups() {
        List<Group> courses = groupRepository.findAll();
        assertEquals(10, courses.size());
    }

    @Test
    void shouldFindGroupById() {
        Optional<Group> optionalGroup = groupRepository.findById(10001);
        assertTrue(optionalGroup.isPresent());
        assertEquals("A-01", optionalGroup.get().getGroupName());
    }

    @Test
    void shouldCreateNewGroup() {
        Group group = new Group();
        group.setGroupName("JC-90");
        Group createdGroup = groupRepository.save(group);
        assertNotNull(createdGroup.getGroupId());
        assertEquals("JC-90", createdGroup.getGroupName());
    }

    @Test
    void shouldUpdateGroup() {
        Group updateGroup = groupRepository.findById(10001).orElseThrow();
        updateGroup.setGroupName("New Group name");
        groupRepository.save(updateGroup);
        assertEquals("New Group name", updateGroup.getGroupName());
    }


    @Test
    void shouldDeleteGroupById() {
        groupRepository.deleteById(10009);
        Optional<Group> deletedGroup = groupRepository.findById(10009);
        assertTrue(deletedGroup.isEmpty());
    }

    @Test
    void shouldFindGroupByStudentCount() {
        List<Group> groupsByStudentCount = groupRepository.findByStudentsLessThanEqual(30);

        assertEquals(10, groupsByStudentCount.size());
    }
}
