package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteRepository
 */
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

  List<Restaurante> consultarPorNome(String nome);

  Page<Restaurante> findAll(Specification<Restaurante> spec, Pageable page);

  Page<Restaurante> findAll(Pageable page);

}
