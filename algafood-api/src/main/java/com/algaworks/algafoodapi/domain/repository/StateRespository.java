package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.State;

/**
 * EstadoRespository
 */
public interface StateRespository extends JpaRepository<State, Long> {
}
