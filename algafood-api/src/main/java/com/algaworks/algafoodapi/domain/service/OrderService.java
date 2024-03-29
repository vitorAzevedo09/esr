package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.OrderItemNotFoundException;
import com.algaworks.algafoodapi.domain.exception.OrderNotFoundException;
import com.algaworks.algafoodapi.domain.model.Order;
import com.algaworks.algafoodapi.domain.model.OrderItem;
import com.algaworks.algafoodapi.domain.repository.OrderItemRespository;
import com.algaworks.algafoodapi.domain.repository.OrderRepository;

/**
 * OrderService
 */
@Service
public class OrderService {

  @Autowired
  private OrderRepository oRepository;

  @Autowired
  private OrderItemRespository oiRepository;

  public Page<Order> findAll(Specification<Order> spec, Pageable pageable) {
    return oRepository.findAll(spec, pageable);
  }

  public Order findOrFail(final Long orderID) {
    return oRepository.findById(orderID)
        .orElseThrow(() -> new OrderNotFoundException(orderID));
  }

  public OrderItem findOrFailIOrderItem(final Long orderItemId) {
    return oiRepository.findById(orderItemId)
        .orElseThrow(() -> new OrderItemNotFoundException(orderItemId));
  }

  @Transactional
  public void create(Order order) {
    oRepository.save(order);
  }

  @Transactional
  public void updateStatus(final Long id) {
    findOrFail(id).setNextStatus();
  }
}
