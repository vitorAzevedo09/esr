package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.CidadeAssembler;
import com.algaworks.algafoodapi.api.dto.cidade.CidadeInputDTO;
import com.algaworks.algafoodapi.api.dto.cidade.CidadeOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;

/**
 * CidadeService
 */
@Service
public class CidadeService {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private CidadeAssembler cidadeAssembler;

  public Page<CidadeOutputDTO> getAll(Pageable page) {
    return cidadeRepository.findAll(page).map(c -> cidadeAssembler.toOutputDTO(c));
  }

  public CidadeOutputDTO getOne(Long id) {
    Cidade cidade = cidadeRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Cidade with id " + id + " not found", id)));
    return cidadeAssembler.toOutputDTO(cidade);
  }

  @Transactional
  public CidadeOutputDTO create(CidadeInputDTO cidadeIN) {
    Cidade cidade = cidadeAssembler.toEntity(cidadeIN);
    Cidade cidadeSaved = cidadeRepository.save(cidade);
    return cidadeAssembler.toOutputDTO(cidadeSaved);
  }

  @Transactional
  public CidadeOutputDTO update(final Long id, CidadeInputDTO cidadeIn) {

    if (!cidadeRepository.existsById(id)) {
      new ResponseStatusException(HttpStatus.NOT_FOUND,
          String.format("Cidade with id " + id + " not found", id));
    }

    Cidade cidadeUpdate = cidadeAssembler.toEntity(cidadeIn);
    cidadeUpdate.setId(id);

    Cidade cidadeUpdated = cidadeRepository.save(cidadeUpdate);

    return cidadeAssembler.toOutputDTO(cidadeUpdated);
  }

  @Transactional
  public CidadeOutputDTO deletar(final Long id) {
    Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        String.format("Cidade with id " + id + " not found", id)));

    cidadeRepository.deleteById(id);

    return cidadeAssembler.toOutputDTO(cidade);

  }

}
