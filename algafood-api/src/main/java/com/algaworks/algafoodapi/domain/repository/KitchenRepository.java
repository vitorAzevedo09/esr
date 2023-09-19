package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Kitchen;

/**
 * CozinhaRepository
 */
@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {}
