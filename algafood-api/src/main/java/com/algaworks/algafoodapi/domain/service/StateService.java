package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.StateNotFoundException;
import com.algaworks.algafoodapi.domain.model.State;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.domain.repository.StateRespository;

/**
 * EstadoService
 */
@Service
public class StateService {

    @Autowired
    private StateRespository stateRespository;

    @Autowired
    private CityRepository cityRepository;

    public Page<State> findAll(Pageable page) {
        return stateRespository.findAll(page);
    }

    public State findOrFail(final Long id) {
        return stateRespository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @Transactional
    public State create(State inputState) {
        return stateRespository.save(inputState);
    }

    @Transactional
    public State update(final Long id, State inputState) {
        if (!stateRespository.existsById(id)) {
            throw new StateNotFoundException(id);
        }
        inputState.setId(id);
        return stateRespository.save(inputState);
    }

    @Transactional
    public void delete(final Long id) {
        State state = findOrFail(id);
        cityRepository.deleteAll(state.getCities());
        stateRespository.delete(state);
    }
}
