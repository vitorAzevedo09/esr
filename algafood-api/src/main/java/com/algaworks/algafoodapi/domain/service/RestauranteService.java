package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.RestauranteAssembler;
import com.algaworks.algafoodapi.api.dto.RestauranteOutputDTO;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;

/**
 * RestauranteService
 */
@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteAssembler restauranteAssembler;

    public Page<RestauranteOutputDTO> listar(Pageable page) {
        return restauranteAssembler.toPageOutputDto(restauranteRepository.findAll(page));
    }
}
