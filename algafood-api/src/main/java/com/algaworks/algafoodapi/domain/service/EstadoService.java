package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.api.assembler.EstadoAssembler;
import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;
import com.algaworks.algafoodapi.domain.repository.EstadoRespository;

/**
 * EstadoService
 */
@Service
public class EstadoService {

  @Autowired
  private EstadoRespository estadoRespository;

  @Autowired
  private EstadoAssembler estadoAssembler;

  public Page<EstadoOutputDTO> getAll(Pageable page) {
    return estadoRespository.findAll(page).map(e -> estadoAssembler.toOutputDTO(e));
  }

}
