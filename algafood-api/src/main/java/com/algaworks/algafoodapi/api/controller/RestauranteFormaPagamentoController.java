package com.algaworks.algafoodapi.api.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.domain.service.RestauranteService;
import com.algaworks.algafoodapi.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafoodapi.api.dto.forma_pagamento.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteController
 */
@RestController
@RequestMapping("/restaurantes/{id}/forma-pagamento/")
public class RestauranteFormaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @GetMapping
    public List<FormaPagamentoOutput> getAllPaymentMethod(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.findOrFail(id);
        Restaurante restauranteDB = restaurante.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return restauranteDB.getFormasPagamento().stream()
                .map(fp -> formaPagamentoAssembler.toOutputDTO(fp))
                .toList();
    }

}
