package com.algaworks.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.UserAssembler;
import com.algaworks.algafoodapi.api.dto.PasswordInput;
import com.algaworks.algafoodapi.api.dto.UserInput;
import com.algaworks.algafoodapi.api.dto.UserOutput;
import com.algaworks.algafoodapi.api.dto.UserWithPasswordInput;
import com.algaworks.algafoodapi.domain.model.User;
import com.algaworks.algafoodapi.domain.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

/**
 * UsuarioController
 */
@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<UserOutput> getAll(Pageable page) {
        return userService.findAll(page)
                .map(u -> userAssembler.toOutput(u));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserOutput getOne(@PathVariable Long id) {
        User user = userService.findOrFail(id);
        return userAssembler.toOutput(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutput create(@RequestBody @Valid UserWithPasswordInput userInput) {
        User user = userAssembler.toEntity(userInput);
        user = userService.create(user);
        return userAssembler.toOutput(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserOutput update(
            @PathVariable Long id,
            @RequestBody @Valid UserInput userInput) {
        User user = userAssembler.toEntity(userInput);
        userService.update(id, user);
        return userAssembler.toOutput(user);
    }

    @PutMapping("/{id}/atualizar-senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long id, @RequestBody @Valid PasswordInput passwordInput) {
        userService.changePassword(id, passwordInput.actualPassword(), passwordInput.newPassword());
    }

}
