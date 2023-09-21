package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.CuisineNotFoundException;
import com.algaworks.algafoodapi.domain.model.Cuisine;
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
            return kitchenRepository.save(inputCuisine);
        }
        throw new CuisineNotFoundException(id);
    }

    @Transactional
    public void delete(Long id) {
        Cuisine kitchen = findOrFail(id);
        restaurantRepository.deleteAll(kitchen.getRestaurants());
        kitchenRepository.delete(kitchen);
    }

}
