package com.algaworks.algafoodapi.api.assembler;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.RestauranteOutputDTO;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputIdDTO;
import com.algaworks.algafoodapi.api.dto.restaurante.RestauranteInputDTO;
import com.algaworks.algafoodapi.domain.model.Restaurante;

/**
 * RestauranteAssembler
 */
@Component
public class RestauranteAssembler {

    @Autowired
    private CozinhaAssembler cozinhaAssembler;

    public RestauranteOutputDTO toOutputDto(Restaurante restaurante) {
        return new RestauranteOutputDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTaxaFrete(),
                cozinhaAssembler.toOutputDto(restaurante.getCozinha()));
    }

    /**
     * @param restauranteIN Restaurante input DTO
     * @return Entity Restaurante
     */
    public Restaurante toEntity(RestauranteInputDTO restauranteIN) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteIN.nome());
        restaurante.setTaxaFrete(restauranteIN.taxa_frete());
        restaurante.setCozinha(cozinhaAssembler.toEntity(restauranteIN.cozinha()));
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
