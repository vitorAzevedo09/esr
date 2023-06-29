package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.grupo.GrupoDTO;
import com.algaworks.algafoodapi.api.dto.grupo.GrupoInputDTO;
import com.algaworks.algafoodapi.domain.model.Grupo;

/**
 * GrupoAssembler
 */
@Component
public class GrupoAssembler {

  public GrupoDTO toOutputDTO(Grupo grupo) {
    return new GrupoDTO(grupo.getId(), grupo.getNome());
  }

  public Grupo toModel(GrupoInputDTO grupo) {
    Grupo g = new Grupo();
    g.setNome(grupo.nome());
    return g;
  }

  public List<GrupoDTO> toCollectionDTO(List<Grupo> grupos) {
    return grupos.stream().map((this::toOutputDTO)).collect(Collectors.toList());
  }

  public Grupo copyToModel(GrupoDTO input, Grupo grupo) {
    if (input.id() != null) {
      grupo.setId(input.id());
    }
    if (input.nome() != null) {
      grupo.setNome(input.nome());
    }
    return grupo;
  }
}
