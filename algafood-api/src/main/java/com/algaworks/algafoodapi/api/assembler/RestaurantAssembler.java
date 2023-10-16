package com.algaworks.algafoodapi.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.RestaurantIdInput;
import com.algaworks.algafoodapi.api.dto.RestaurantInput;
import com.algaworks.algafoodapi.api.dto.RestaurantOutput;
import com.algaworks.algafoodapi.api.dto.RestaurantResumeOutput;
import com.algaworks.algafoodapi.domain.model.Restaurant;
import com.algaworks.algafoodapi.domain.service.RestaurantService;

/**
 * RestauranteAssembler
 */
@Component
public class RestaurantAssembler {

    @Autowired
    private RestaurantService rService;

    public RestaurantOutput toOutput(Restaurant restaurant) {
        RestaurantOutput restaurantOutput = new RestaurantOutput(restaurant.getId(),
                restaurant.getName(),
                restaurant.getActive());
        return restaurantOutput;
    }

    public RestaurantResumeOutput toResumeOutput(Restaurant restaurant) {
        RestaurantResumeOutput restaurantOutput = new RestaurantResumeOutput(restaurant.getId(),
                restaurant.getName());
        return restaurantOutput;
    }

    /**
     * @param restauranteIN Restaurante input DTO
     * @return Entity Restaurante
     */
    public Restaurant toEntity(RestaurantInput restaurantInput) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInput.name());
        restaurant.setDeliveryFee(restaurantInput.shipping_fee());
        if (restaurant.getActive()) {
            restaurant.active();
        } else {
            restaurant.deactive();
        }
        return restaurant;
    }

    public Restaurant toEntity(RestaurantIdInput restaurantIdInput) {
        return rService.findOrFail(restaurantIdInput.id());
    }

}
