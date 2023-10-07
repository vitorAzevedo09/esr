package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.PermissionAssembler;
import com.algaworks.algafoodapi.api.dto.PermissionOutput;
import com.algaworks.algafoodapi.domain.model.Group;
import com.algaworks.algafoodapi.domain.service.GroupService;

/**
 * GroupPermissionController
 */
@RestController
@RequestMapping("grupos/{groupID}/permissao")
public class GroupPermissionController {

  @Autowired
  private PermissionAssembler pAssembler;

  @Autowired
  private GroupService gService;

  @GetMapping
  public Page<PermissionOutput> getAll(
      @PathVariable final Long groupID,
      Pageable pageable) {
    Group group = gService.findOrFail(groupID);
    return pAssembler.toPage(group.getPermissions(), pageable);
  }

  @PutMapping("/{permissionID}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void addPermissionInGroup(@PathVariable final Long groupID, @PathVariable final Long permissionID) {
    gService.addPermission(groupID, permissionID);
  }

  @DeleteMapping("/{permissionID}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void removePermissionInGroup(@PathVariable final Long groupID, @PathVariable final Long permissionID) {
    gService.removePermission(groupID, permissionID);
  }
}
