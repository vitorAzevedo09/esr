package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.UserAssembler;
import com.algaworks.algafoodapi.api.dto.UserOutput;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

/**
 * RestaurantUserController
 */
@RestController
@RequestMapping("/restaurantes/{restaurantID}/usuarios")
public class RestaurantUserController {

  @Autowired
  private RestaurantService rService;

  @Autowired
  private UserAssembler uAssembler;

  @GetMapping
  public Page<UserOutput> getAll(
      @PathVariable final Long restaurantID,
      Pageable pageable) {
    Restaurant restaurant = rService.findOrFail(restaurantID);
    List<UserOutput> usersOutputs = restaurant.getUsers()
        .stream()
        .map(u -> uAssembler.toOutput(u))
        .toList();
    return new PageImpl<>(usersOutputs, pageable, usersOutputs.size());
  }

  @PutMapping("/{userID}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void addUserInRestaurant(
      @PathVariable final Long restaurantID,
      @PathVariable final Long userID) {
    rService.addUser(restaurantID, userID);
  }

  @DeleteMapping("/{userID}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void removeUserInRestaurant(
      @PathVariable final Long restaurantID,
      @PathVariable final Long userID) {
    rService.removeUser(restaurantID, userID);
  }

}
