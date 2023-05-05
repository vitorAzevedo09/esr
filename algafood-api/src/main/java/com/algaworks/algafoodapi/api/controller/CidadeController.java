package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.cidade.CidadeOutputDTO;
import com.algaworks.algafoodapi.domain.service.CidadeService;

/**
 * CidadeController
 */
@RestController
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeService cidadeService;

  @GetMapping
  public Page<CidadeOutputDTO> listar(Pageable page) {
    return cidadeService.getAll(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CidadeOutputDTO> buscar(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(cidadeService.getOne(id));
  }
}
