
package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Group;

/**
 * CidadeRepository
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
