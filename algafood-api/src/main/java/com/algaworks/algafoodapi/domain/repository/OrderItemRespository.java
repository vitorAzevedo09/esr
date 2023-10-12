package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.OrderItem;

/**
 * OrderItemRespository
 */
@Repository
public interface OrderItemRespository extends JpaRepository<OrderItem, Long> {
}
