package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafoodapi.api.dto.forma_pagamento.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;

/**
 * FormaPagamentoService
 */
@Service
public class FormaPagamentoService {

  @Autowired
  FormaPagamentoAssembler formaPagamentoAssembler;

  @Autowired
  FormaPagamentoRepository formaPagamentoRepository;

  public Page<FormaPagamentoOutput> getAll(Pageable pageable) {
    return formaPagamentoRepository.findAll(pageable)
        .map(fp -> formaPagamentoAssembler.toOutputDTO(fp));
  }

}
