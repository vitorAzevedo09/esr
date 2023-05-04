package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CidadeRepository
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
