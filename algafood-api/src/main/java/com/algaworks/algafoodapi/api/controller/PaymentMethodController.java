package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.PaymentMethodAssembler;
import com.algaworks.algafoodapi.api.dto.PaymentMethodOutput;
import com.algaworks.algafoodapi.domain.service.PaymentMethodService;

/**
 * FormaPagamentoController
 */

@RestController
@RequestMapping("/formas-pagamento")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentMethodAssembler paymentMethodAssembler;

    @GetMapping
    public Page<PaymentMethodOutput> getAll(Pageable pageable) {
        return paymentMethodService.findAll(pageable)
                .map((pm) -> paymentMethodAssembler.toOutput(pm));
    }
}
