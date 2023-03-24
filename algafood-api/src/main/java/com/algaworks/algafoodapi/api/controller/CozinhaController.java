package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.schemas.output.CozinhaOutput;
import com.algaworks.algafoodapi.domain.service.CozinhaService;

import lombok.AllArgsConstructor;

/**
 * CozinhaController
 */
@RestController
@RequestMapping("/cozinhas")
@AllArgsConstructor
public class CozinhaController {

    private CozinhaService cozinhaService;

    @GetMapping
    public Page<CozinhaOutput> listar(Pageable page){
        return cozinhaService.listar(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaOutput> buscar(@PathVariable Long id){
        return cozinhaService.buscar(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
