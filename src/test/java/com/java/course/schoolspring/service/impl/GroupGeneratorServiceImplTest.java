package com.java.course.schoolspring.service.impl;


import com.java.course.schoolspring.dao.GroupRepository;
import com.java.course.schoolspring.model.Group;
import com.java.course.schoolspring.service.GroupGeneratorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {GroupGeneratorServiceImpl.class})
public class GroupGeneratorServiceImplTest {
    @MockBean
    GroupRepository groupRepository;

    @Autowired
    GroupGeneratorService groupGeneratorService;

    @Captor
    ArgumentCaptor<List<Group>> groupCaptor;

    @Test
    void generatedGroupWhenNeed() {
        when(groupRepository.count()).thenReturn(0L);

        groupGeneratorService.generateGroupsIfNeed();

        verify(groupRepository, times(1)).saveAll(groupCaptor.capture());

        List<Group> capturedGroup = groupCaptor.getValue();
        assertEquals(10, capturedGroup.size());
    }


}
