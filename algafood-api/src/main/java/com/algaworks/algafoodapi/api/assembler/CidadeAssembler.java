package com.algaworks.algafoodapi.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.cidade.CidadeOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cidade;

/**
 * CidadeAssembler
 */
@Component
public class CidadeAssembler {

  @Autowired
  private EstadoAssembler estadoAssembler;

  public CidadeOutputDTO toOutputDTO(Cidade cidade) {
    return new CidadeOutputDTO(cidade.getId(), cidade.getNome(), estadoAssembler.toOutputDTO(cidade.getEstado()));
  }

}
