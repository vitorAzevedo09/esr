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

import com.algaworks.algafoodapi.domain.model.PaymentMethod;
import com.algaworks.algafoodapi.domain.service.PaymentMethodService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class PaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentMethodService paymentMethodService;

    @BeforeEach
    void setUp(){
        when(paymentMethodService.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(createPaymentMethod())));
    }

    @Test
    public void testGetAllPaymentMethods() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/formas-pagamento")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("Payment Method Name"));
    }

    PaymentMethod createPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1L);
        paymentMethod.setDescription("Payment Method Name");
        return paymentMethod;
    }
}
