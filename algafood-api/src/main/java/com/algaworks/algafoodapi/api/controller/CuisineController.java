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

import com.algaworks.algafoodapi.api.assembler.CuisineAssembler;
import com.algaworks.algafoodapi.api.dto.CuisineInput;
import com.algaworks.algafoodapi.api.dto.CuisineOutput;
import com.algaworks.algafoodapi.domain.model.Cuisine;
import com.algaworks.algafoodapi.domain.service.CuisineService;

/**
 * CozinhaController
 */
@RestController
@RequestMapping("/cozinhas")
public class CuisineController {

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private CuisineAssembler cuisineAssembler;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<CuisineOutput> getAll(Pageable page) {
        return cuisineService.findAll(page)
                .map((c) -> cuisineAssembler.toOutput(c));
}

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CuisineOutput getOne(@PathVariable Long id) {
        Cuisine cuisine = cuisineService.findOrFail(id);
        return cuisineAssembler.toOutput(cuisine);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CuisineOutput create(@Valid @RequestBody CuisineInput cuisineInput) {
        Cuisine cuisine = cuisineAssembler.toEntity(cuisineInput);
        cuisine = cuisineService.create(cuisine);
        return cuisineAssembler.toOutput(cuisine);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CuisineOutput update(@PathVariable Long id, @Valid @RequestBody CuisineInput cuisineInput) {
        Cuisine cuisine = cuisineAssembler.toEntity(cuisineInput);
        cuisine = cuisineService.update(id, cuisine);
        return cuisineAssembler.toOutput(cuisine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuisineService.delete(id);
    }
}
