package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;

/**
 * CozinhaRepository
 */
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {}
