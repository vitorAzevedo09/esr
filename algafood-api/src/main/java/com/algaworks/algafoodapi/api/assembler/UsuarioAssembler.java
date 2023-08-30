package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.dto.usuario.UsuarioInputComSenha;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioInputDTO;
import com.algaworks.algafoodapi.api.dto.usuario.UsuarioOutputDTO;
import com.algaworks.algafoodapi.domain.model.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * UsuarioAssembler
 */
@Component
public class UsuarioAssembler {

    public Usuario toEntity(UsuarioInputDTO input) {
        return Usuario.Builder
                .newInstance()
                .setNome(input.nome())
                .setEmail(input.email())
                .build();
    }

    public Usuario toEntity(UsuarioInputComSenha input) {
        return Usuario.Builder
                .newInstance()
                .setNome(input.nome())
                .setEmail(input.email())
                .setSenha(input.senha())
                .build();
    }

    public UsuarioOutputDTO toOutputDto(Usuario domainModel) {
        return new UsuarioOutputDTO(
                domainModel.getId(),
                domainModel.getNome(),
                domainModel.getEmail());
    }

    public Page<UsuarioOutputDTO> toPageOutputDto(Page<Usuario> userPage) {
        return userPage.map(p -> toOutputDto(p));
    }
}
