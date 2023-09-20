package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.GroupAssembler;
import com.algaworks.algafoodapi.api.dto.GroupInput;
import com.algaworks.algafoodapi.api.dto.GroupOutput;
import com.algaworks.algafoodapi.domain.model.Group;
import com.algaworks.algafoodapi.domain.service.GroupService;

/**
 * GrupoController
 */
@RestController
@RequestMapping("/grupos")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupAssembler groupAssembler;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<GroupOutput> getAll(Pageable pageable) {
        return groupService.findAll(pageable)
                .map((g) -> groupAssembler.toOutput(g));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public GroupOutput getOne(@PathVariable Long id) {
        Group group = groupService.findOrFail(id);
        return groupAssembler.toOutput(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupOutput create(@RequestBody @Valid GroupInput groupInput) {
        Group group = groupAssembler.toEntity(groupInput);
        group = groupService.create(group);
        return groupAssembler.toOutput(group);
    }

    @PutMapping("/{id}")
    public GroupOutput update(
            @PathVariable Long id,
            @RequestBody @Valid GroupInput groupInput) {
        Group group = groupAssembler.toEntity(groupInput);
        group = groupService.update(id, group);
        return groupAssembler.toOutput(group);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

}
