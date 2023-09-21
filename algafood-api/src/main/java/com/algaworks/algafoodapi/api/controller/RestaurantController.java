package com.algaworks.algafoodapi.api.controller;

import java.util.Map;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.RestaurantAssembler;
import com.algaworks.algafoodapi.api.dto.RestaurantInput;
import com.algaworks.algafoodapi.api.dto.RestaurantOutput;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

/**
 * RestauranteController
 */
@RestController
@RequestMapping("/restaurantes")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantAssembler restaurantAssembler;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<RestaurantOutput> getAll(Pageable page) {
        return restaurantService.findAll(page)
                .map((r) -> restaurantAssembler.toOutput(r));
    }

    @GetMapping("/por-nome")
    @ResponseStatus(code = HttpStatus.OK)
    public List<RestaurantOutput> findByName(@RequestParam(value = "name") String name) {
        return restaurantService.findByName(name).stream()
                .map((r) -> restaurantAssembler.toOutput(r)).toList();
    }

    @GetMapping("/por-nome/frete-gratis")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<RestaurantOutput> findByNameAndFreeShipping(
            Pageable page,
            @RequestParam(value = "name") String name) {
        return restaurantService.findByNameAndFreeShipping(page, name)
                .map((r) -> restaurantAssembler.toOutput(r));
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public RestaurantOutput getOne(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findOrFail(id);
        return restaurantAssembler.toOutput(restaurant);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RestaurantOutput create(@Valid @RequestBody RestaurantInput restaurantInput) {
        Restaurant restaurant = restaurantAssembler.toEntity(restaurantInput);
        restaurant = restaurantService.create(restaurant);
        return restaurantAssembler.toOutput(restaurant);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public RestaurantOutput update(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantInput restaurantInput) {
        Restaurant restaurant = restaurantAssembler.toEntity(restaurantInput);
        restaurant = restaurantService.update(id, restaurant);
        return restaurantAssembler.toOutput(restaurant);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public RestaurantOutput partialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> fields) {
        Restaurant restaurant = restaurantService.partialUpdate(id, fields);
        return restaurantAssembler.toOutput(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(code = HttpStatus.OK)
    public void ativar(@PathVariable("id") final Long id) {
        restaurantService.active(id);
    }

    @DeleteMapping("/{id}/ativo")
    public void desativar(@PathVariable("id") final Long id) {
        restaurantService.deactive(id);
    }
}
