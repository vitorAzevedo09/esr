package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Restaurant;

/**
 * RestauranteRepository
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {

  List<Restaurant> findByName(String name);

  Page<Restaurant> findAll(Specification<Restaurant> spec, Pageable page);

  Page<Restaurant> findAll(Pageable page);

}
