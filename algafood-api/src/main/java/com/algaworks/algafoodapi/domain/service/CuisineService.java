package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.CuisineNotFoundException;
import com.algaworks.algafoodapi.domain.model.Cuisine;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.repository.CuisineRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;

/**
 * CozinhaService
 */
@Service
public class CuisineService {

    @Autowired
    private CuisineRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductService productService;

    public Page<Cuisine> findAll(Pageable page) {
        return kitchenRepository.findAll(page);
    }

    public Cuisine findOrFail(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new CuisineNotFoundException(id));
    }

    @Transactional
    public Cuisine create(Cuisine inputCuisine) {
        return kitchenRepository.save(inputCuisine);
    }

    @Transactional
    public Cuisine update(final Long id, Cuisine inputCuisine) {
        if (kitchenRepository.existsById(id)) {
            inputCuisine.setId(id);
            return kitchenRepository.save(inputCuisine);
        }
        throw new CuisineNotFoundException(id);
    }

    @Transactional
    public void delete(Long id) {
        Cuisine cuisine = findOrFail(id);
        for (Restaurant restaurant: cuisine.getRestaurants()) {
            productService.deleteAllByRestaurant(restaurant);
        }
        restaurantRepository.deleteAll(cuisine.getRestaurants());
        kitchenRepository.delete(cuisine);
    }

}
