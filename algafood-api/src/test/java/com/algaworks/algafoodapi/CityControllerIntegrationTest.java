package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @BeforeEach
    public void setUp() {
        // Mock the behavior of your CityService methods
        when(cityService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(createCity())));
        when(cityService.findOrFail(anyLong()))
                .thenReturn(createCity());
        when(cityService.create(any(City.class)))
                .thenReturn(createCity());
        when(cityService.update(anyLong(), any(City.class)))
                .thenReturn(createCity());
    }

    @Test
    public void testGetAllCities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cidades")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("CityName"));
    }

    @Test
    public void testGetCityById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cidades/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CityName"));
    }

    @Test
    public void testCreateCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"CityName\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CityName"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/cidades/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"CityName\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CityName"));
    }

    // Add more test cases for other controller methods and edge cases as needed

    private City createCity() {
        City city = new City();
        city.setId(1L);
        city.setName("CityName");
        // Set other properties as needed
        return city;
    }
}
