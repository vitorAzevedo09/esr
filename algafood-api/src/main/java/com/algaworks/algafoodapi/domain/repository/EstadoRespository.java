package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.Estado;

/**
 * EstadoRespository
 */
public interface EstadoRespository extends JpaRepository<Estado, Long> {
}
