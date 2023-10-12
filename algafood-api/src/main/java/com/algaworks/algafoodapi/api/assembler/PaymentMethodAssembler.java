package com.algaworks.algafoodapi.api.assembler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.PaymentMethodIdInput;
import com.algaworks.algafoodapi.api.dto.PaymentMethodOutput;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;
import com.algaworks.algafoodapi.domain.service.PaymentMethodService;

/**
 * FormaPagamentoAssembler
 */
@Component
public class PaymentMethodAssembler {

  @Autowired
  private PaymentMethodService pmService;

  public PaymentMethodOutput toOutput(PaymentMethod paymentMethod) {
    return new PaymentMethodOutput(paymentMethod.getId(), paymentMethod.getDescription());
  }

  public PaymentMethod toEntity(PaymentMethodIdInput paymentMethodIdInput) {
    return pmService.findOrFail(paymentMethodIdInput.id());
  }

  public Page<PaymentMethodOutput> toPage(List<PaymentMethod> payments, Pageable pageable) {
    List<PaymentMethodOutput> paymentsOutput = payments.stream()
        .map(p -> toOutput(p))
        .toList();
    return new PageImpl<PaymentMethodOutput>(paymentsOutput, pageable, payments.size());
  }
}
