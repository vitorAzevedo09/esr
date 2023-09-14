package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.domain.model.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Optional<Usuario> exist = usuarioRepository.findByEmail(usuario.getEmail());
        if(exist.isPresent() && !exist.get().equals(usuario)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Usuario com este email já existente" );
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioID, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioID);
        if(usuario.senhaNaoCoincideCom(senhaAtual)){
			throw new RuntimeException("Senha atual informada não coincide com a senha do usuário.");
        }
    }

    @Transactional
    public Usuario buscarOuFalhar(Long usuarioID) {
        return usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
