package com.algaworks.algafoodapi.api.assembler;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.CozinhaInputDTO;
import com.algaworks.algafoodapi.api.dto.CozinhaOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

/**
 * CozinhaAssembler
 */
@AllArgsConstructor
@Component
public class CozinhaAssembler {
    private ModelMapper modelMapper;

    public CozinhaOutputDTO toDto(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaOutputDTO.class);
    }

    public Page<CozinhaOutputDTO> toCollectionDto(Page<Cozinha> cozinhas){
        return cozinhas.map(this::toDto);
    }

    public Cozinha toEntity(CozinhaInputDTO cozinha){
        return modelMapper.map(cozinha, Cozinha.class);
    }
}
