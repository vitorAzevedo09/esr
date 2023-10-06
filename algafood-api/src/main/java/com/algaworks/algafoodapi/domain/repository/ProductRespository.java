package com.algaworks.algafoodapi.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Product;
import com.algaworks.algafoodapi.domain.model.Restaurant;

/**
 * ProductRespository
 */
@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {

  @Query("from products where restaurant.id = :restaurant and id = :product")
  Optional<Product> findById(
      @Param("restaurant") Long restaurantID,
      @Param("product") Long productID);

  Page<Product> findByRestaurant(Restaurant restaurant, Pageable pageable);

}
