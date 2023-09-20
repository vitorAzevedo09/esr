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

import com.algaworks.algafoodapi.api.assembler.KitchenAssembler;
import com.algaworks.algafoodapi.api.dto.KitchenInput;
import com.algaworks.algafoodapi.api.dto.KitchenOutput;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.service.KitchenService;

/**
 * CozinhaController
 */
@RestController
@RequestMapping("/cozinhas")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenAssembler kitchenAssembler;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<KitchenOutput> getAll(Pageable page) {
        return kitchenService.findAll(page)
                .map((k) -> kitchenAssembler.toOutput(k));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public KitchenOutput getOne(@PathVariable Long id) {
        Kitchen kitchen = kitchenService.findOrFail(id);
        return kitchenAssembler.toOutput(kitchen);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public KitchenOutput create(@Valid @RequestBody KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenAssembler.toEntity(kitchenInput);
        kitchen = kitchenService.create(kitchen);
        return kitchenAssembler.toOutput(kitchen);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public KitchenOutput update(@PathVariable Long id, @Valid @RequestBody KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenAssembler.toEntity(kitchenInput);
        kitchen = kitchenService.update(id, kitchen);
        return kitchenAssembler.toOutput(kitchen);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }
}
