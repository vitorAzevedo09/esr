package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.model.Order;
import com.algaworks.algafoodapi.domain.repository.OrderRepository;

/**
 * OrderService
 */
@Service
public class OrderService {

  @Autowired
  private OrderRepository oRepository;

  public Page<Order> findAll(Pageable pageable) {
    return oRepository.findAll(pageable);
  }

}
