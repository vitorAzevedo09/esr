package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteRepository
 */
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

  Page<Restaurante> consultarPorNome(String nome, Pageable page);

}
