package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.CozinhaAssembler;
import com.algaworks.algafoodapi.api.dto.CozinhaInputDTO;
import com.algaworks.algafoodapi.api.dto.CozinhaOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

import lombok.AllArgsConstructor;

/**
 * CozinhaService
 */
@Service
@AllArgsConstructor
public class CozinhaService {

    private CozinhaRepository cozinhaRepository;
    private CozinhaAssembler cozinhaAssembler;

    public Page<CozinhaOutputDTO> listar(Pageable page){
        Page<Cozinha> modelCozinhas = cozinhaRepository.findAll(page);
        return cozinhaAssembler.toCollectionDto(modelCozinhas); 
    }

    public Optional<CozinhaOutputDTO> buscar(Long id){
        return cozinhaRepository.findById(id).map(cozinhaAssembler::toDto);
    }


    @Transactional
    public Optional<CozinhaOutputDTO> criar(CozinhaInputDTO cozinhaInput){
        Cozinha cozinha = cozinhaAssembler.toEntity(cozinhaInput);
        Cozinha cozinhaFromDb = cozinhaRepository.save(cozinha);
        return Optional.of(cozinhaAssembler.toDto(cozinhaFromDb));
    }

    @Transactional
    public Optional<CozinhaOutputDTO> atualizar(CozinhaInputDTO cozinhaInput, Long id) {
        Cozinha cozinha = cozinhaAssembler.toEntity(cozinhaInput);
        cozinha.setId(id);
        return Optional.of(cozinhaAssembler.toDto(cozinhaRepository.save(cozinha)));
    }

    @Transactional
    public Optional<CozinhaOutputDTO> deletar(Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        cozinhaRepository.delete(cozinha.get());
        return Optional.of(cozinhaAssembler.toDto(cozinha.get()));
    }
    
}
