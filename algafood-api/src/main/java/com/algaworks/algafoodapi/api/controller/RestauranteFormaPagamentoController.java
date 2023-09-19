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

import com.algaworks.algafoodapi.domain.service.RestauranteService;
import com.algaworks.algafoodapi.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafoodapi.api.dto.forma_pagamento.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteController
 */
@RestController
@RequestMapping("/restaurantes/{idRestaurant}/forma-pagamento/")
public class RestauranteFormaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @GetMapping
    public List<FormaPagamentoOutput> getAllPaymentMethod(@PathVariable Long idRestaurant) {
        Restaurante restaurante = restauranteService.findOrFail(idRestaurant);
        Restaurante restauranteDB = restaurante;
        return restauranteDB.getFormasPagamento().stream()
                .map(fp -> formaPagamentoAssembler.toOutputDTO(fp))
                .toList();
    }

    @DeleteMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Long idRestaurant, @PathVariable Long idPaymentMethod) {
        restauranteService.removePaymentMethod(idRestaurant, idPaymentMethod);
    }

}
