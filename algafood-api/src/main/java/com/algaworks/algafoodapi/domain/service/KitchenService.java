package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.KitchenNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;

/**
 * CozinhaService
 */
@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Page<Kitchen> findAll(Pageable page) {
        return kitchenRepository.findAll(page);
    }

    public Kitchen findOrFail(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

    @Transactional
    public Kitchen create(Kitchen inputKitchen) {
        return kitchenRepository.save(inputKitchen);
    }

    @Transactional
    public Kitchen update(final Long id, Kitchen inputKitchen) {
        if (kitchenRepository.existsById(id)) {
            return kitchenRepository.save(inputKitchen);
        }
        throw new KitchenNotFoundException(id);
    }

    @Transactional
    public void delete(Long id) {
        Kitchen kitchen = findOrFail(id);
        restaurantRepository.deleteAll(kitchen.getRestaurantes());
        kitchenRepository.delete(kitchen);
    }

}
