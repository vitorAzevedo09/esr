package com.algaworks.algafoodapi.api.controller;

import java.time.OffsetDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.OrderAssembler;
import com.algaworks.algafoodapi.api.dto.OrderInput;
import com.algaworks.algafoodapi.api.dto.OrderOutput;
import com.algaworks.algafoodapi.domain.service.OrderService;
import static com.algaworks.algafoodapi.infrastructure.repository.OrderSpecs.*;

/** OrderController */
@RestController
@RequestMapping("/pedidos")
public class OrderController {

  @Autowired
  private OrderService oService;

  @Autowired
  private OrderAssembler oAssembler;

  @GetMapping
  public Page<OrderOutput> getAll(
      Pageable pageable,
      @RequestParam(name = "restaurante_id", required = false) final Long restaurantID,
      @RequestParam(name = "cliente_id", required = false) final Long clientID,
      @RequestParam(name = "data_inicio", required = false) final OffsetDateTime begin,
      @RequestParam(name = "data_fim", required = false) final OffsetDateTime end) {
    return oService.findAll(
        withClientEqualsTo(clientID)
            .and(withRestaurantEqualsTo(restaurantID))
            .and(withCreationDateGreaterThan(begin))
            .and(withCreationDateLessThan(end)),
        pageable)
        .map(o -> oAssembler.toOutput(o));
  }

  @GetMapping("/{orderID}")
  public OrderOutput getOne(@PathVariable final Long orderID) {
    return oAssembler.toOutput(oService.findOrFail(orderID));
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public void create(@Valid @RequestBody OrderInput oInput) {
    oService.create(oAssembler.toEntity(oInput));
  }

  @PutMapping("/{orderID}/next-status")
  @ResponseStatus(code = HttpStatus.OK)
  public void updateStatus(@PathVariable final Long orderID) {
    oService.updateStatus(orderID);
  }

}
