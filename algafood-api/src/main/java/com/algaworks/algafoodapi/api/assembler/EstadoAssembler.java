package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.estado.EstadoInputDTO;
import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;
import com.algaworks.algafoodapi.domain.model.Estado;

/**
 * EstadoAssembler
 */
@Component
public class EstadoAssembler {

  public EstadoOutputDTO toOutputDTO(Estado estado) {
    return new EstadoOutputDTO(estado.getId(), estado.getNome());
  }

  public Estado toEntity(EstadoInputDTO estadoIn) {
    Estado estado = new Estado();
    estado.setNome(estadoIn.nome());
    return estado;
  }

}
