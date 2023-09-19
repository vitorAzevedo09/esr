package com.algaworks.algafoodapi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.ProdutoAssembler;
import com.algaworks.algafoodapi.api.dto.produto.ProdutoOutputDTO;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import java.util.List;

/**
 * RestauranteProdutoController
 */
@Controller
@RequestMapping("/restaurante/{idRestaurante}/")
public class RestauranteProdutoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoAssembler produtoAssembler;

    @GetMapping
    public Page<ProdutoOutputDTO> getAll(@PathVariable Long idRestaurante, Pageable page) {
        Optional<Restaurante> restaurante = restauranteService.findOrFail(idRestaurante);
        Restaurante restauranteExists = restaurante
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Produto> produtos = restauranteExists.getProdutos();
        return produtoAssembler.toPageDTO(produtoAssembler.listToPage(produtos, page));
    }
}
