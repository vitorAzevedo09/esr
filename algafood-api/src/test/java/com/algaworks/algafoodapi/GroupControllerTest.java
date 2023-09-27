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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @BeforeEach
    void setUp(){
        when(groupService.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(createGroup())));
        when(groupService.findOrFail(anyLong()))
            .thenReturn(createGroup());
        when(groupService.create(any(Group.class)))
            .thenReturn(createGroup());
        when(groupService.update(anyLong(),any(Group.class)))
            .thenReturn(createGroup());
        doNothing()
            .when(groupService)
            .delete(anyLong());
    }

    @Test
    public void testGetAllGroups() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/grupos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Group Name"));

    }

    @Test
    public void testGetGroupById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/grupos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Group Name"));
    }

    @Test
    public void testCreateGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/grupos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Group Name\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Group Name"));
    }

    @Test
    public void testUpdateGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/grupos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Group Name\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Group Name"));
    }

    @Test
    public void testDeleteGroup() throws Exception {
        Long groupIdToDelete = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/grupos/{id}", groupIdToDelete))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    Group createGroup() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Group Name");
        return group;
    }
}
