package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.exception.ProductNotFoundException;
import com.algaworks.algafoodapi.domain.model.Product;
import com.algaworks.algafoodapi.domain.repository.ProductRespository;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private RestaurantService restaurantService;

    public void deleteAllByRestaurant(Restaurant restaurant) {
        productRespository.deleteAll(restaurant.getProducts());
    }

    @Transactional
    public Product save(Product produto) {
        return productRespository.save(produto);
    }

    public Product findOrFail(Long restaurantID, Long productID) {
        return productRespository.findById(restaurantID, productID)
                .orElseThrow(() -> new ProductNotFoundException(restaurantID, productID));
    }

    public Page<Product> findByRestaurant(final Long restaurantID, Pageable pageable) {
        Restaurant restaurant = restaurantService.findOrFail(restaurantID);
        return productRespository.findByRestaurant(restaurant, pageable);
    }
}
