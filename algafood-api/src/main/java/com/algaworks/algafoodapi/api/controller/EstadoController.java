package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
