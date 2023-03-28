package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputDTO;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaOutputDTO;
import com.algaworks.algafoodapi.domain.service.CozinhaService;

/**
 * CozinhaController
 */
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public Page<CozinhaOutputDTO> listar(Pageable page) {
        return cozinhaService.listar(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaOutputDTO> buscar(@PathVariable Long id) {
        return cozinhaService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CozinhaOutputDTO> criar(@Valid @RequestBody CozinhaInputDTO cozinhaInput) {
        return cozinhaService.criar(cozinhaInput)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CozinhaOutputDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody CozinhaInputDTO cozinhaInput) {
        return cozinhaService.atualizar(cozinhaInput, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CozinhaOutputDTO> name(@PathVariable Long id) {
        return cozinhaService.deletar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }
}
