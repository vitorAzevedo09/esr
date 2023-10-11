package com.algaworks.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.OrderAssembler;
import com.algaworks.algafoodapi.api.dto.OrderInput;
import com.algaworks.algafoodapi.api.dto.OrderOutput;
import com.algaworks.algafoodapi.domain.service.OrderService;

/**
 * OrderController
 */
@RestController
@RequestMapping("/pedidos")
public class OrderController {

  @Autowired
  private OrderService oService;

  @Autowired
  private OrderAssembler oAssembler;

  @GetMapping
  public Page<OrderOutput> getAll(Pageable pageable) {
    return oService.findAll(pageable)
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

}
