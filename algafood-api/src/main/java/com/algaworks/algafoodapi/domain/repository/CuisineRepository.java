package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Cuisine;

/**
 * CozinhaRepository
 */
@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {}
