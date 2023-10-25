package com.algaworks.algafoodapi.infrastructure.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafoodapi.domain.model.Order;

/**
 * OrderSpecs
 */
public class OrderSpecs {

  public static Specification<Order> withRestaurantEqualsTo(final Long restaurantID) {
    return (root, query, builder) -> restaurantID != null ? builder.equal(root.get("restaurant"), restaurantID) : null;
  }

  public static Specification<Order> withClientEqualsTo(final Long clientID) {
    return (root, query, builder) -> clientID != null ? builder.equal(root.get("client"), clientID) : null;
  }

  public static Specification<Order> withCreationDateLessThan(final OffsetDateTime end) {
    return (root, query, builder) -> end != null ? builder.lessThanOrEqualTo(root.get("clientID"), end) : null;
  }

  public static Specification<Order> withCreationDateGreaterThan(final OffsetDateTime begin) {
    return (root, query, builder) -> begin != null ? builder.greaterThanOrEqualTo(root.get("clientID"), begin) : null;
  }

}
