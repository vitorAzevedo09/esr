package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.PaymentMethodAssembler;
import com.algaworks.algafoodapi.api.dto.PaymentMethodOutput;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;
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
    public Page<PaymentMethodOutput> getAllPaymentMethod(@PathVariable Long idRestaurant, Pageable pageable) {
        Restaurant restaurant = restaurantService.findOrFail(idRestaurant);
        List<PaymentMethod> payments = restaurant.getPaymentMethods();
        return paymentMethodAssembler.toPage(payments, pageable);
    }

    @DeleteMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Long idRestaurant, @PathVariable Long idPaymentMethod) {
        restaurantService.removePaymentMethod(idRestaurant, idPaymentMethod);
    }

}
