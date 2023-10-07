package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.PermissionNotFoundException;
import com.algaworks.algafoodapi.domain.model.Permission;
import com.algaworks.algafoodapi.domain.repository.PermissionRepository;

/**
 * PermissionService
 */
@Service
public class PermissionService {

  @Autowired
  private PermissionRepository pRepository;

  public Permission findOrFail(final Long id) {
    return pRepository.findById(id)
        .orElseThrow(() -> new PermissionNotFoundException(id));
  }
}
