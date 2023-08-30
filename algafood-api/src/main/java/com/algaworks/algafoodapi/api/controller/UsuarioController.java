package com.algaworks.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.UsuarioAssembler;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioOutputDTO;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * UsuarioController
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    @GetMapping
    public Page<UsuarioOutputDTO> name(Pageable page) {
        return usuarioRepository.findAll(page).map(u -> usuarioAssembler.toOutputDto(u));
    }

}
