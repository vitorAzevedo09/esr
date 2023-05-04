package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

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

}
