package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.forma_pagamento.FormaPagamentoOutput;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;

/**
 * FormaPagamentoAssembler
 */
@Component
public class FormaPagamentoAssembler {

  public FormaPagamentoOutput toOutputDTO(FormaPagamento forma_pagamento) {
    return new FormaPagamentoOutput(forma_pagamento.getId(), forma_pagamento.getDescricao());
  }

}
