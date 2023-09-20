package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.PaymentMethodAssembler;
import com.algaworks.algafoodapi.api.dto.PaymentMethodOutput;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

/**
 * RestaurantPaymentMethodController
 */
@RestController
@RequestMapping("/restaurantes/{idRestaurant}/formas-pagamento")
public class RestaurantPaymentMethodController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodAssembler paymentMethodAssembler;

    @GetMapping
    public List<PaymentMethodOutput> getAllPaymentMethod(@PathVariable Long idRestaurant) {
        Restaurant restaurant = restaurantService.findOrFail(idRestaurant);
        return restaurant.getPaymentMethods().stream()
                .map((pm) -> paymentMethodAssembler.toOutput(pm))
                .toList();
    }

    @DeleteMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Long idRestaurant, @PathVariable Long idPaymentMethod) {
        restaurantService.removePaymentMethod(idRestaurant, idPaymentMethod);
    }

}
