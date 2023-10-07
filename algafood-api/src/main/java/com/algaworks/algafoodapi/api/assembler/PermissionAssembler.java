package com.algaworks.algafoodapi.api.assembler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.PermissionInput;
import com.algaworks.algafoodapi.api.dto.PermissionOutput;
import com.algaworks.algafoodapi.domain.model.Permission;

/**
 * PermissionAssembler
 */
@Component
public class PermissionAssembler {

  public Permission toEntity(PermissionInput permissionInput) {
    return new Permission(permissionInput.name(), permissionInput.description());
  }

  public PermissionOutput toOutput(Permission permission) {
    return new PermissionOutput(
        permission.getId(),
        permission.getName(),
        permission.getDescription());
  }

  public Page<PermissionOutput> toPage(List<Permission> permissions, Pageable pageable) {
    List<PermissionOutput> permissionOutputs = permissions.stream()
        .map(p -> toOutput(p)).toList();
    return new PageImpl<>(permissionOutputs, pageable, permissionOutputs.size());
  }

}
