package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.Product;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RestaurantService restaurantService;

  @Test
  public void testGetAllProducts() throws Exception {
    Long restaurantId = 1L;

    // Create a restaurant with some products
    Restaurant restaurant = new Restaurant();
    restaurant.setId(restaurantId);
    restaurant.getProducts().add(createProduct());

    Mockito.when(restaurantService.findOrFail(restaurantId)).thenReturn(restaurant);

    mockMvc.perform(MockMvcRequestBuilders.get("/restaurantes/{restaurantId}/produtos/", restaurantId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Pizza"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("Delicious pizza"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].price").value("10.0"));
  }

  Product createProduct() {
    Product product = new Product();
    product.setId(1L);
    product.setName("Pizza");
    product.setDescription("Delicious pizza");
    product.setPrice(new BigDecimal("10.00"));
    return product;
  }
}
