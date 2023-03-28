package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.RestauranteOutputDTO;
import com.algaworks.algafoodapi.domain.service.RestauranteService;

/**
 * RestauranteController
 */
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;
    
    @GetMapping
    public Page<RestauranteOutputDTO> listar(Pageable page) {
        return restauranteService.listar(page);
    }
}
