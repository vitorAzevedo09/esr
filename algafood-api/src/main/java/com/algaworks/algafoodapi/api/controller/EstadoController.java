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

import com.algaworks.algafoodapi.api.dto.estado.EstadoInputDTO;
import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;
import com.algaworks.algafoodapi.domain.service.EstadoService;

/**
 * EstadoController
 */
@RestController
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoService estadoService;

  @GetMapping
  public Page<EstadoOutputDTO> listar(Pageable page) {
    return estadoService.getAll(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EstadoOutputDTO> buscar(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(estadoService.getOne(id));
  }

  @PostMapping
  public ResponseEntity<EstadoOutputDTO> criar(@Valid @RequestBody EstadoInputDTO estadoIn) {
    return ResponseEntity.ok(estadoService.create(estadoIn));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EstadoOutputDTO> atualizar(@PathVariable("id") final Long id,
      @Valid @RequestBody EstadoInputDTO estadoIn) {
    return ResponseEntity.ok(estadoService.update(id, estadoIn));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EstadoOutputDTO> excluir(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(estadoService.delete(id));
  }
}
