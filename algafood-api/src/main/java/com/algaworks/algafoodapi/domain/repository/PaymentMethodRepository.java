package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.PaymentMethod;

/**
 * FormaPagamentoRepository
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
