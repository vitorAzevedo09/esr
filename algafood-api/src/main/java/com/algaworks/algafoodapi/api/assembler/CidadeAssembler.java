package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.cidade.CidadeInputDTO;
import com.algaworks.algafoodapi.api.dto.cidade.CidadeOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cidade;

/**
 * CidadeAssembler
 */
@Component
public class CidadeAssembler {

  public CidadeOutputDTO toOutputDTO(Cidade cidade) {
    return new CidadeOutputDTO(cidade.getId(), cidade.getNome());
  }

  public Cidade toEntity(CidadeInputDTO cidadeIN) {
    Cidade cidade = new Cidade();
    cidade.setNome(cidadeIN.name());
    return cidade;
  }

}
