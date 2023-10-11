package com.algaworks.algafoodapi.api.assembler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.PaymentMethodIdInput;
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

  public PaymentMethod toEntity(PaymentMethodIdInput paymentMethodIdInput) {
    PaymentMethod pm = new PaymentMethod();
    pm.setId(paymentMethodIdInput.id());
    return pm;
  }

  public Page<PaymentMethodOutput> toPage(List<PaymentMethod> payments, Pageable pageable) {
    List<PaymentMethodOutput> paymentsOutput = payments.stream()
        .map(p -> toOutput(p))
        .toList();
    return new PageImpl<PaymentMethodOutput>(paymentsOutput, pageable, payments.size());
  }
}
