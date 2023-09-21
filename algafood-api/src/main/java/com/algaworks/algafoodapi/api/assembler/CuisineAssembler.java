package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.CuisineInput;
import com.algaworks.algafoodapi.api.dto.CuisineOnlyIdOutput;
import com.algaworks.algafoodapi.api.dto.CuisineOutput;
import com.algaworks.algafoodapi.domain.model.Cuisine;

/**
 * CuisineAssembler
 */
@Component
public class CuisineAssembler {

    public CuisineOutput toOutput(Cuisine cuisine) {
        return new CuisineOutput(cuisine.getId(), cuisine.getName());
    }

    public CuisineOnlyIdOutput toOutputOnlyId(Cuisine cuisine) {
        return new CuisineOnlyIdOutput(cuisine.getId());
    }

    public Cuisine toEntity(CuisineInput cuisineInput) {
        Cuisine cuisine = new Cuisine();
        cuisine.setName(cuisineInput.name());
        return cuisine;
    }
}
