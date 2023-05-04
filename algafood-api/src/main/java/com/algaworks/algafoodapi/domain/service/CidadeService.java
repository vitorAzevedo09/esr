package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.CidadeAssembler;
import com.algaworks.algafoodapi.api.dto.cidade.CidadeOutputDTO;
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

}
