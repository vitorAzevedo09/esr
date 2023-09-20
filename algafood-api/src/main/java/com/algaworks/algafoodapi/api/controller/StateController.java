package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.StateAssembler;
import com.algaworks.algafoodapi.api.dto.StateInput;
import com.algaworks.algafoodapi.api.dto.StateOutput;
import com.algaworks.algafoodapi.domain.model.State;
import com.algaworks.algafoodapi.domain.service.StateService;

/**
 * EstadoController
 */
@RestController
@RequestMapping("/estados")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private StateAssembler stateAssembler;

    @GetMapping
    public Page<StateOutput> getAll(Pageable page) {
        return stateService.findAll(page)
                .map((s) -> stateAssembler.toOutput(s));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public StateOutput getOne(@PathVariable("id") final Long id) {
        State state = stateService.findOrFail(id);
        return stateAssembler.toOutput(state);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StateOutput create(@Valid @RequestBody StateInput stateInput) {
        State state = stateAssembler.toEntity(stateInput);
        state = stateService.create(state);
        return stateAssembler.toOutput(state);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public StateOutput update(
            @PathVariable("id") final Long id,
            @Valid @RequestBody StateInput stateInput) {
        State state = stateAssembler.toEntity(stateInput);
        state = stateService.update(id, state);
        return stateAssembler.toOutput(state);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        stateService.delete(id);
    }
}
