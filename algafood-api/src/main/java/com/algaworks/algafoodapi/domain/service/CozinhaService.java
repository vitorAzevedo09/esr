package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.CozinhaAssembler;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputDTO;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;

/**
 * CozinhaService
 */
@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaAssembler cozinhaAssembler;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Page<CozinhaOutputDTO> listar(Pageable page) {
        Page<Cozinha> modelCozinhas = cozinhaRepository.findAll(page);
        return cozinhaAssembler.toPageOutputDto(modelCozinhas);
    }

    public Optional<CozinhaOutputDTO> buscar(Long id) {
        return cozinhaRepository.findById(id).map(cozinhaAssembler::toOutputDto);
    }

    @Transactional
    public Optional<CozinhaOutputDTO> criar(CozinhaInputDTO cozinhaInput) {
        Cozinha cozinha = cozinhaAssembler.toEntity(cozinhaInput);
        Cozinha cozinhaFromDb = cozinhaRepository.save(cozinha);
        return Optional.of(cozinhaAssembler.toOutputDto(cozinhaFromDb));
    }

    @Transactional
    public Optional<CozinhaOutputDTO> atualizar(CozinhaInputDTO cozinhaInput, Long id) {
        Cozinha cozinha = cozinhaAssembler.toEntity(cozinhaInput);
        cozinha.setId(id);
        return Optional.of(cozinhaAssembler.toOutputDto(cozinhaRepository.save(cozinha)));
    }

    @Transactional
    public CozinhaOutputDTO deletar(Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Cozinha com id " + id + " não encontrada", id)));
        for (Restaurante restaurante : cozinha.getRestaurantes()) {
            restauranteRepository.delete(restaurante);
        }

        cozinhaRepository.delete(cozinha);
        return cozinhaAssembler.toOutputDto(cozinha);
    }

}
