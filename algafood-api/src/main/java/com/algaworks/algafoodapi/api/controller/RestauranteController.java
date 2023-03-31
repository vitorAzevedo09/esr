package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.RestauranteOutputDTO;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> buscar(@PathVariable Long id) {
        return restauranteService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestauranteOutputDTO> criar(@Valid @RequestBody RestauranteInputDTO restauranteIN) {
        return restauranteService.criar(restauranteIN).map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.internalServerError().build());
    }
}
