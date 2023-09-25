package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.RestaurantInput;
import com.algaworks.algafoodapi.api.dto.RestaurantOutput;
import com.algaworks.algafoodapi.domain.model.Restaurant;

/**
 * RestauranteAssembler
 */
@Component
public class RestaurantAssembler {

    public RestaurantOutput toOutput(Restaurant restaurant) {
        RestaurantOutput restaurantOutput = new RestaurantOutput(restaurant.getId(),
                restaurant.getName(),
                restaurant.getDeliveryFee(),
                restaurant.getActive());
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

}
