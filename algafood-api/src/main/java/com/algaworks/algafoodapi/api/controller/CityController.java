package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.CityAssembler;
import com.algaworks.algafoodapi.api.dto.CityOutput;
import com.algaworks.algafoodapi.domain.service.CityService;

/**
 * CityController
 */
@RestController
@RequestMapping("/cidades")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityAssembler cityAssembler;

    @GetMapping
    public Page<CityOutput> listar(Pageable page) {
        return cityService.findAll(page)
                .map((c) -> cityAssembler.toOutput(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeOutputDTO> buscar(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(cidadeService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<CidadeOutputDTO> criar(@Valid @RequestBody CidadeInputDTO cidadeIN) {
        return ResponseEntity.ok(cidadeService.create(cidadeIN));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeOutputDTO> atualizar(@PathVariable("id") final Long id,
            @Valid @RequestBody CidadeInputDTO cidadeIn) {
        return ResponseEntity.ok(cidadeService.update(id, cidadeIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CidadeOutputDTO> deletar(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(cidadeService.deletar(id));
    }

}
