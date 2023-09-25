package com.algaworks.algafoodapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.algaworks.algafoodapi.domain.model.Group;
import com.algaworks.algafoodapi.domain.service.GroupService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class GroupControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @BeforeEach
    void setUp(){
        when(groupService.findAll(any(Pageable.class)))
    .thenReturn(new PageImpl<>(Collections.singletonList(createGroup())));
    }

    @Test
    public void testGetAllGroups() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/groups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0]").value("Group Name"));

    }

    @Test
    public void testGetOne() throws Exception {

    }

    Group createGroup() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Name Group");
        return group;
    }
}
