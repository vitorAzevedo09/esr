
package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Grupo;

/**
 * CidadeRepository
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
