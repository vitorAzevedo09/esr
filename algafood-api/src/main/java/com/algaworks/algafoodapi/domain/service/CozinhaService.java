package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.CozinhaAssembler;
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
        return cozinhaAssembler.toCollectionSchema(modelCozinhas); 
    }

    public Optional<CozinhaOutputDTO> buscar(Long id){
        return cozinhaRepository.findById(id).map(cozinhaAssembler::toSchema);
    }
    
}
