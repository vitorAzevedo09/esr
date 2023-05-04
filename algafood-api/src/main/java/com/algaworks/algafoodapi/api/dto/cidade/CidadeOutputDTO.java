package com.algaworks.algafoodapi.api.dto.cidade;

import com.algaworks.algafoodapi.api.dto.estado.EstadoOutputDTO;

/**
 * CidadeOutputDTO
 */
public record CidadeOutputDTO(Long id, String nome, EstadoOutputDTO estado) {
}
