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

import com.algaworks.algafoodapi.api.assembler.CityAssembler;
import com.algaworks.algafoodapi.api.dto.CityInput;
import com.algaworks.algafoodapi.api.dto.CityOutput;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.service.CityService;

/**
 * CityController
 */
@RestController
@RequestMapping("/cidades")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityAssembler cityAssembler;

    @GetMapping
    public Page<CityOutput> getAll(Pageable page) {
        return cityService.findAll(page)
                .map((c) -> cityAssembler.toOutput(c));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CityOutput getOne(@PathVariable final Long id) {
        City city = cityService.findOrFail(id);
        return cityAssembler.toOutput(city);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CityOutput create(@Valid @RequestBody CityInput cityInput) {
        City city = cityAssembler.toEntity(cityInput);
        city = cityService.create(city);
        return cityAssembler.toOutput(city);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CityOutput update(@PathVariable("id") final Long id, @Valid @RequestBody CityInput cityInput) {
        City city = cityAssembler.toEntity(cityInput);
        city = cityService.update(id, city);
        return cityAssembler.toOutput(city);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        cityService.delete(id);
    }

}
