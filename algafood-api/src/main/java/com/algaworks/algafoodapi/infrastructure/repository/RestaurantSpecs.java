package com.algaworks.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafoodapi.domain.model.Restaurant;

/**
 * RestauranteSpecs
 */
public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeShipping() {
        return (root, query, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarNames(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}
