package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;

/**
 * FormaPagamentoRepository
 */
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
