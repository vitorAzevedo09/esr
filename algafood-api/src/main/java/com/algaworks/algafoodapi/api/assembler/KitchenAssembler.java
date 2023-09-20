package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.KitchenInput;
import com.algaworks.algafoodapi.api.dto.KitchenOnlyIdOutput;
import com.algaworks.algafoodapi.api.dto.KitchenOutput;
import com.algaworks.algafoodapi.domain.model.Kitchen;

/**
 * KitchenAssembler
 */
@Component
public class KitchenAssembler {

    public KitchenOutput toOutput(Kitchen kitchen) {
        return new KitchenOutput(kitchen.getId(), kitchen.getName());
    }

    public KitchenOnlyIdOutput toOutputOnlyId(Kitchen kitchen) {
        return new KitchenOnlyIdOutput(kitchen.getId());
    }

    public Kitchen toEntity(KitchenInput kitchenInput) {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(kitchenInput.name());
        return kitchen;
    }
}
