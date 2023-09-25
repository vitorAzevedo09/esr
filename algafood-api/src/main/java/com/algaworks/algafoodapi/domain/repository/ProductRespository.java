package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Product;

/**
 * ProductRespository
 */
@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {

}
