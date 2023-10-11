package com.algaworks.algafoodapi.api.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * OrderInput
 */
public record OrderInput(
        @Valid @NotNull RestaurantIdInput restaurant,

        @Valid @NotNull AddressOutput address_delivery,

        @Valid @NotNull PaymentMethodIdInput payment_method,

        @Valid @Size(min = 1) @NotNull List<OrderItemInput> items) {
}
