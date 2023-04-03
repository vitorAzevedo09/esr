package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputDTO;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaInputIdDTO;
import com.algaworks.algafoodapi.api.dto.cozinha.CozinhaOutputDTO;
import com.algaworks.algafoodapi.domain.model.Cozinha;

/**
 * CozinhaAssembler
 */
@Component
public class CozinhaAssembler {

    public CozinhaOutputDTO toOutputDto(Cozinha cozinha) {
        return new CozinhaOutputDTO(cozinha.getId(), cozinha.getNome());
    }

    public CozinhaInputIdDTO toInputIdDto(Cozinha cozinha) {
        return new CozinhaInputIdDTO(cozinha.getId());
    }

    public Page<CozinhaOutputDTO> toPageOutputDto(Page<Cozinha> cozinhas) {
        return cozinhas.map(this::toOutputDto);
    }

    public List<CozinhaOutputDTO> toCollectionOutputDto(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toOutputDto)
                .collect(Collectors.toList());

    }

    public Cozinha toEntity(CozinhaInputDTO cozinha) {
        Cozinha cozinhaEntity = new Cozinha();
        cozinhaEntity.setNome(cozinha.nome());
       return cozinhaEntity; 
    }

    public Cozinha toEntity(CozinhaInputIdDTO cozinha) {
        Cozinha cozinhaEntity = new Cozinha();
        if(cozinha != null){
            cozinhaEntity.setId(cozinha.id());
        }
       return cozinhaEntity; 
    }
}
