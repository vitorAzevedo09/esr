package com.algaworks.algafoodapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import com.algaworks.algafoodapi.domain.service.CuisineService;
import com.algaworks.algafoodapi.domain.model.Cuisine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CuisineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuisineService cuisineService;

    @BeforeEach
    void setUp() {
        // Mock the behavior of your CuisineService methods
        when(cuisineService.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(createCuisine())));

        when(cuisineService.findOrFail(anyLong()))
                .thenReturn(createCuisine());

        when(cuisineService.create(any(Cuisine.class)))
                .thenReturn(createCuisine());

        when(cuisineService.update(anyLong(), any(Cuisine.class)))
                .thenReturn(createCuisine());
        doNothing()
            .when(cuisineService)
            .delete(anyLong());
    }

    @Test
    public void testGetAllCuisines() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cozinhas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("CuisineName"));
    }

    @Test
    public void testGetCuisineById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cozinhas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CuisineName"));
    }

    @Test
    public void testCreateCuisine() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cozinhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"CuisineName\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CuisineName"));
    }

    @Test
    public void testUpdateCuisine() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/cozinhas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"CuisineName\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CuisineName"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        Long cityIdToDelete = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/cozinhas/{id}", cityIdToDelete))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    Cuisine createCuisine() {
        Cuisine cuisine = new Cuisine();
        cuisine.setId(1L);
        cuisine.setName("CuisineName");
        return cuisine;
    }
}
