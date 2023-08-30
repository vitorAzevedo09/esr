package com.algaworks.algafoodapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.UsuarioAssembler;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioInputComSenha;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioInputDTO;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioOutputDTO;
import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import com.algaworks.algafoodapi.domain.service.UsuarioService;

import javax.validation.Valid;

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

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioOutputDTO> getAll(Pageable page) {
        return usuarioRepository.findAll(page).map(u -> usuarioAssembler.toOutputDto(u));
    }

    @GetMapping("/{id}")
    public UsuarioOutputDTO getOne(@PathVariable Long id) {
        return usuarioAssembler.toOutputDto(usuarioService.buscarOuFalhar(id));
    }

    @PostMapping
    public UsuarioOutputDTO add(@RequestBody @Valid UsuarioInputComSenha inputUser) {
        Usuario usuario = usuarioAssembler.toEntity(inputUser);
        usuario = usuarioService.salvar(usuario);
        return usuarioAssembler.toOutputDto(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioOutputDTO update(@PathVariable Long id,
            @RequestBody @Valid UsuarioInputDTO userInput) {
        Usuario actualUser = usuarioService.buscarOuFalhar(id);
        Usuario user = usuarioAssembler.copytoEntity(userInput,actualUser);
        user.setId(actualUser.getId());
        user = usuarioService.salvar(user);
        return usuarioAssembler.toOutputDto(user);
    }
}
