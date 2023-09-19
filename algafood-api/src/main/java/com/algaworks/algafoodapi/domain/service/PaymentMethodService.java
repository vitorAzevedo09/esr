package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.PaymentMethodNotFoundException;
import com.algaworks.algafoodapi.domain.model.PaymentMethod;
import com.algaworks.algafoodapi.domain.repository.PaymentMethodRepository;

/**
 * FormaPagamentoService
 */
@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public Page<PaymentMethod> findAll(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    public PaymentMethod findOrFail(final Long id){
        return paymentMethodRepository.findById(id).orElseThrow(() -> new PaymentMethodNotFoundException(id));
    }

}
