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

import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RestaurantService restaurantService;

  @BeforeEach
    void setUp(){
        when(restaurantService.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(createRestaurant())));
        when(restaurantService.findOrFail(anyLong()))
            .thenReturn(createRestaurant());
        when(restaurantService.create(any(Restaurant.class)))
            .thenReturn(createRestaurant());
        when(restaurantService.update(anyLong(),any(Restaurant.class)))
            .thenReturn(createRestaurant());
        doNothing()
            .when(restaurantService)
            .delete(anyLong());
    }

  @Test
  public void testGetAllrestaurantes() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/restaurantes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Restaurant Name"));
  }

  @Test
  public void testGetRestaurantById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/restaurantes/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Restaurant Name"));
  }

  @Test
  public void testCreateRestaurant() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/restaurantes")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Restaurant Name\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Restaurant Name"));
  }

  @Test
  public void testUpdateRestaurant() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put("/restaurantes/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Restaurant Name\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Restaurant Name"));
  }

  @Test
  public void testDeleteRestaurant() throws Exception {
    Long restaurantIdToDelete = 1L;

    mockMvc.perform(MockMvcRequestBuilders.delete("/restaurantes/{id}", restaurantIdToDelete))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  Restaurant createRestaurant() {
    Restaurant restaurant = new Restaurant();
    restaurant.setId(1L);
    restaurant.setName("Restaurant Name");
    return restaurant;
  }
}
