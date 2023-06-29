
package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;

@Service
public class GrupoService {

  private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
  private static final String MSG_GRUPO_NAO_ENCONTRADO = "Estado com o id %d não encontrado";

  @Autowired
  private GrupoRepository grupoRepository;

  @Transactional
  public Grupo salvar(Grupo grupo) {
    return grupoRepository.save(grupo);
  }

  @Transactional
  public void excluir(Long grupoId) {
    try {
      grupoRepository.deleteById(grupoId);
      grupoRepository.flush();

    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          String.format(MSG_GRUPO_NAO_ENCONTRADO, grupoId));

    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          String.format(MSG_GRUPO_EM_USO, grupoId));
    }
  }

  public Grupo buscarOuFalhar(Long grupoId) {
    return grupoRepository.findById(grupoId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
            String.format(MSG_GRUPO_EM_USO, grupoId)));
  }

}
