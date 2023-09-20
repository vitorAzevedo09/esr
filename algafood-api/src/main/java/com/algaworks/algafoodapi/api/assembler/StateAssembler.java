package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.StateInput;
import com.algaworks.algafoodapi.api.dto.StateOutput;
import com.algaworks.algafoodapi.domain.model.State;

/**
 * StateAssembler
 */
@Component
public class StateAssembler {

    public StateOutput toOutput(State state) {
        return new StateOutput(state.getId(), state.getName());
    }

    public State toEntity(StateInput stateInput) {
        State state = new State();
        state.setName(stateInput.name());
        return state;
    }

}
