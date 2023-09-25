package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.ProductRespository;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    public void deleteAllByRestaurant(Restaurant restaurant){
        productRespository.deleteAll(restaurant.getProducts());
    }
    
}
