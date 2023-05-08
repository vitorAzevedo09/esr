package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafoodapi.api.assembler.EstadoAssembler;
import com.algaworks.algafoodapi.api.dto.estado.EstadoInputDTO;
import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRespository;

/**
 * EstadoService
 */
@Service
public class EstadoService {

  @Autowired
  private EstadoRespository estadoRespository;

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoAssembler estadoAssembler;

  public Page<EstadoOutputDTO> getAll(Pageable page) {
    return estadoRespository.findAll(page).map(e -> estadoAssembler.toOutputDTO(e));
  }

  public EstadoOutputDTO getOne(final Long id) {
    return estadoRespository.findById(id).map(e -> estadoAssembler.toOutputDTO(e))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Estado com o id " + id + " não encontrado ", id)));

  }

  @Transactional
  public EstadoOutputDTO create(EstadoInputDTO estadoIn) {
    Estado estado = estadoAssembler.toEntity(estadoIn);
    Estado estadoSaved = estadoRespository.save(estado);
    return estadoAssembler.toOutputDTO(estadoSaved);
  }

  @Transactional
  public EstadoOutputDTO update(final Long id, EstadoInputDTO estadoIn) {
    if (!estadoRespository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          String.format("Estado com o id " + id + " não encontrado", id));
    }

    Estado estado = estadoAssembler.toEntity(estadoIn);
    estado.setId(id);

    Estado estadoUpdate = estadoRespository.save(estado);
    return estadoAssembler.toOutputDTO(estadoUpdate);
  }

  @Transactional
  public EstadoOutputDTO delete(final Long id) {
    Estado estado = estadoRespository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Estado com o id " + id + " não encontrado", id)));

    for (Cidade cidade : estado.getCidades()) {
      cidadeRepository.delete(cidade);
    }

    estadoRespository.delete(estado);
    return estadoAssembler.toOutputDTO(estado);
  }
}
