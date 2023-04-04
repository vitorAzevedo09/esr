package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.RestauranteAssembler;
import com.algaworks.algafoodapi.api.dto.RestauranteOutputDTO;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;

/**
 * RestauranteService
 */
@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteAssembler restauranteAssembler;

    public Page<RestauranteOutputDTO> listar(Pageable page) {
        return restauranteAssembler.toPageOutputDto(restauranteRepository.findAll(page));
    }

    public Optional<RestauranteOutputDTO> buscar(Long id) {
        Optional<Restaurante> restauranteDB = restauranteRepository.findById(id);
        return restauranteDB.map(r -> Optional.of(restauranteAssembler.toOutputDto(r)))
                .orElse(null);
    }

    @Transactional
    public Optional<RestauranteOutputDTO> criar(RestauranteInputDTO restauranteIN) {
        Restaurante restaurante = restauranteRepository.save(restauranteAssembler.toEntity(restauranteIN));
        Optional<Cozinha> cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha.get());
        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restaurante);
        return Optional.of(restauranteOUT);
    }

    @Transactional
    public Optional<RestauranteOutputDTO> atualizar(Long id, RestauranteInputDTO restauranteIN){
        Restaurante restauranteToUpdate = restauranteAssembler.toEntity(restauranteIN);
        restauranteToUpdate.setId(id);

        Restaurante restauranteUpdated = restauranteRepository.saveAndFlush(restauranteToUpdate);

        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restauranteUpdated);

        return Optional.of(restauranteOUT);
    }

    @Transactional
    public Optional<RestauranteOutputDTO> deletar(Long id) {
        Optional<Restaurante> restauranteDB = restauranteRepository.findById(id);

        restauranteRepository.deleteById(id);

        RestauranteOutputDTO restauranteOUT = restauranteAssembler.toOutputDto(restauranteDB.get());
        return Optional.of(restauranteOUT);
    }
}
