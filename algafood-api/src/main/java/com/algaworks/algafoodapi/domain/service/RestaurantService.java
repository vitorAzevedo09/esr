package com.algaworks.algafoodapi.domain.service;

import static com.algaworks.algafoodapi.infrastructure.repository.RestaurantSpecs.withFreeShipping;
import static com.algaworks.algafoodapi.infrastructure.repository.RestaurantSpecs.withSimilarNames;

import java.util.Map;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.model.PaymentMethod;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.model.User;
import com.algaworks.algafoodapi.domain.repository.RestaurantRepository;
import com.algaworks.algafoodapi.domain.exception.RestaurantNotFoundException;

/**
 * RestauranteService
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService uService;

    public Page<Restaurant> findAll(Pageable page) {
        return restaurantRepository.findAll(page);
    }

    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Page<Restaurant> findByNameAndFreeShipping(Pageable page, String name) {
        return restaurantRepository.findAll(withFreeShipping().and(withSimilarNames(name)), page);
    }

    public Restaurant findOrFail(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant create(Restaurant restaurantInput) {
        return restaurantRepository.save(restaurantInput);
    }

    @Transactional
    public Restaurant update(final Long id, Restaurant restaurantInput) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }
        restaurantInput.setId(id);
        return restaurantRepository.saveAndFlush(restaurantInput);
    }

    @Transactional
    public Restaurant partialUpdate(Long id, Map<String, Object> fields) {
        Restaurant restaurant = findOrFail(id);
        restaurant.setFromMap(fields);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }
        restaurantRepository.deleteById(id);
    }

    @Transactional
    public void active(final Long id) {
        Restaurant restaurant = findOrFail(id);
        restaurant.active();
    }

    @Transactional
    public void deactive(final Long id) {
        Restaurant restaurant = findOrFail(id);
        restaurant.deactive();
    }

    @Transactional
    public void removePaymentMethod(Long idRestaurant, Long idPaymentMethod) {
        Restaurant restaurant = findOrFail(idRestaurant);
        PaymentMethod paymentMethod = paymentMethodService.findOrFail(idPaymentMethod);
        restaurant.getPaymentMethods().remove(paymentMethod);
    }

    @Transactional
    public void addPaymentMethod(Long idRestaurant, Long idPaymentMethod) {
        Restaurant restaurant = findOrFail(idRestaurant);
        PaymentMethod paymentMethod = paymentMethodService.findOrFail(idPaymentMethod);
        restaurant.getPaymentMethods().add(paymentMethod);
    }

    @Transactional
    public void removeUser(Long restaurantID, Long userID) {
        Restaurant restaurant = findOrFail(restaurantID);
        User user = uService.findOrFail(userID);
        restaurant.getUsers().remove(user);
    }

    @Transactional
    public void addUser(Long restaurantID, Long userID) {
        Restaurant restaurant = findOrFail(restaurantID);
        User user = uService.findOrFail(userID);
        restaurant.getUsers().add(user);
    }

    @Transactional
    public void close(final Long id) {
        Restaurant restaurant = findOrFail(id);
        restaurant.close();
    }

    @Transactional
    public void open(final Long id) {
        Restaurant restaurant = findOrFail(id);
        restaurant.open();
    }

}
