package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

/**
 * CozinhaService
 */
@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Page<Cozinha> listar(Pageable page){
        return cozinhaRepository.findAll(page); 
    }
    
}
