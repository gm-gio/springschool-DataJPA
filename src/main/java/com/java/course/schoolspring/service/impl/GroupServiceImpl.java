package com.java.course.schoolspring.service.impl;

import com.java.course.schoolspring.dao.GroupRepository;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;


    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findGroupsWithLessOrEqualStudentCount(int maxStudentCount) {
        return groupRepository.findByStudentsLessThanEqual(maxStudentCount);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findGroupById(int groupId) {
        return groupRepository.findById(groupId);
    }
}
