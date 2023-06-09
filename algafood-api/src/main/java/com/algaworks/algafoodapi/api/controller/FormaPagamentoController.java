package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.dto.forma_pagamento.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;

/**
 * FormaPagamentoController
 */

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

  @Autowired
  FormaPagamentoService formaPagamentoService;

  @GetMapping
  public Page<FormaPagamentoOutput> listar(Pageable pageable) {
    return formaPagamentoService.getAll(pageable);
  }
}
