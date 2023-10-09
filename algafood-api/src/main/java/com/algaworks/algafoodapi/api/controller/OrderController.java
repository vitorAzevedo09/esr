package com.algaworks.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.OrderAssembler;
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

}
