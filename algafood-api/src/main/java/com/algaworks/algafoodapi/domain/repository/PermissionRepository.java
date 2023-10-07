package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Permission;

/**
 * PermissionRepository
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
