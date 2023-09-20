package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.PaymentMethodOutput;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;

/**
 * FormaPagamentoAssembler
 */
@Component
public class PaymentMethodAssembler {

  public PaymentMethodOutput toOutput(PaymentMethod paymentMethod) {
    return new PaymentMethodOutput(paymentMethod.getId(), paymentMethod.getDescription());
  }

}
