package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.domain.model.PaymentMethod;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantPaymentMethodControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RestaurantService restaurantService;

  @Test
  public void testGetAllPaymentMethods() throws Exception {
    Long restaurantId = 1L;

    // Create a restaurant with some payment methods
    Restaurant restaurant = new Restaurant();
    restaurant.setId(restaurantId);

    PaymentMethod paymentMethod1 = new PaymentMethod();
    paymentMethod1.setId(1L);
    paymentMethod1.setDescription("Credit Card");

    PaymentMethod paymentMethod2 = new PaymentMethod();
    paymentMethod2.setId(2L);
    paymentMethod2.setDescription("Cash");

    restaurant.setPaymentMethods(Arrays.asList(paymentMethod1, paymentMethod2));

    Mockito.when(restaurantService.findOrFail(restaurantId)).thenReturn(restaurant);

    mockMvc.perform(MockMvcRequestBuilders.get("/restaurantes/{idRestaurant}/formas-pagamento", restaurantId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(paymentMethod1.getId()))
        .andExpect(jsonPath("$[0].description").value(paymentMethod1.getDescription()))
        .andExpect(jsonPath("$[1].id").value(paymentMethod2.getId()))
        .andExpect(jsonPath("$[1].description").value(paymentMethod2.getDescription()));
  }

  @Test
  public void testRemovePaymentMethod() throws Exception {
    Long restaurantId = 1L;
    Long paymentMethodId = 1L;

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/restaurantes/{idRestaurant}/formas-pagamento/{idPaymentMethod}",
            restaurantId, paymentMethodId))
        .andExpect(status().isNoContent());

    Mockito.verify(restaurantService).removePaymentMethod(restaurantId, paymentMethodId);
  }
}
