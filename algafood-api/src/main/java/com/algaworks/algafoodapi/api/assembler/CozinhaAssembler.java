package com.algaworks.algafoodapi.api.assembler;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.schemas.input.CozinhaInput;
import com.algaworks.algafoodapi.api.schemas.output.CozinhaOutput;
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

    public CozinhaOutput toSchema(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaOutput.class);
    }

    public Page<CozinhaOutput> toCollectionSchema(Page<Cozinha> cozinhas){
        return cozinhas.map(this::toSchema);
    }

    public Cozinha toEntity(CozinhaInput cozinha){
        return modelMapper.map(cozinha, Cozinha.class);
    }
}
