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

import com.algaworks.algafoodapi.api.assembler.GroupAssembler;
import com.algaworks.algafoodapi.api.dto.GroupOutput;
import com.algaworks.algafoodapi.domain.model.User;
import com.algaworks.algafoodapi.domain.service.UserService;

/**
 * UserGroupController
 */
@RestController
@RequestMapping("/usuarios/{userID}/grupos")
public class UserGroupController {

  @Autowired
  private UserService uService;

  @Autowired
  private GroupAssembler gAssembler;

  @GetMapping
  public Page<GroupOutput> getAll(@PathVariable final Long userID, Pageable pageable) {
    User user = uService.findOrFail(userID);
    return gAssembler.fromListEntitytoPageOutput(user.getGroups(), pageable);
  }

  @PutMapping("/{groupID}")
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public void addGroup(
      @PathVariable final Long userID,
      @PathVariable final Long groupID) {
    uService.addGroupToUser(userID, groupID);
  }

  @DeleteMapping("/{groupID}")
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public void removeGroup(
      @PathVariable final Long userID,
      @PathVariable final Long groupID) {
    uService.removeGroupToUser(userID, groupID);
  }
}
