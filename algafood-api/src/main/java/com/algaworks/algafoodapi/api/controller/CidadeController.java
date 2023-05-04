package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public Page<CidadeOutputDTO> listar(Pageable page) {
    return cidadeService.getAll(page);
  }
}
