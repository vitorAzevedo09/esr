package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.EstadoAssembler;
import com.algaworks.algafoodapi.api.dto.estado.EstadoInputDTO;
import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;
import com.algaworks.algafoodapi.domain.model.Estado;
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

  public EstadoOutputDTO getOne(final Long id) {
    return estadoRespository.findById(id).map(e -> estadoAssembler.toOutputDTO(e))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Estado não encontrado com o id " + id, id)));

  }

  public EstadoOutputDTO create(EstadoInputDTO estadoIn) {
    Estado estado = estadoAssembler.toEntity(estadoIn);
    Estado estadoSaved = estadoRespository.save(estado);
    return estadoAssembler.toOutputDTO(estadoSaved);
  }

}
