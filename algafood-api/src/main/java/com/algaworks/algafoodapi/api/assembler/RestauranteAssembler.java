package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteOutputDTO;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteAssembler
 */
@Component
public class RestauranteAssembler {

    public RestauranteOutputDTO toOutputDto(Restaurante restaurante) {
        RestauranteOutputDTO restauranteOut = new RestauranteOutputDTO(restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTaxaFrete(),
                restaurante.getAtivo());
        return restauranteOut;
    }

    /**
     * @param restauranteIN Restaurante input DTO
     * @return Entity Restaurante
     */
    public Restaurante toEntity(RestauranteInputDTO restauranteIN) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteIN.nome());
        restaurante.setTaxaFrete(restauranteIN.taxa_frete());
        if (restaurante.getAtivo()) {
            restaurante.active();
        } else
            restaurante.deactive();
        return restaurante;
    }

    public Page<RestauranteOutputDTO> toPageOutputDto(Page<Restaurante> restaurantes) {
        return restaurantes.map(this::toOutputDto);
    }

    public List<RestauranteOutputDTO> toCollectionOutputDto(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toOutputDto)
                .collect(Collectors.toList());
    }

}
