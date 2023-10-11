package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * OrderOutput
 */
public record OrderOutput(
        Long id,
        BigDecimal subtotal,
        BigDecimal shippingFee,
        BigDecimal totalValue,
        AddressOutput address,
        LocalDateTime creationDate,
        LocalDateTime confirmationDate,
        LocalDateTime cancellationDate,
        LocalDateTime deliveryDate,
        PaymentMethodOutput paymentMethod,
        RestaurantOutput restaurant) {

    public static OrderOutputBuilder builder() {
        return new OrderOutputBuilder();
    }

    // Getters for all fields (including public getters for the record components)

    public static class OrderOutputBuilder {
        private Long id;
        private BigDecimal subtotal;
        private BigDecimal shippingFee;
        private BigDecimal totalValue;
        private AddressOutput address;
        private LocalDateTime creationDate;
        private LocalDateTime confirmationDate;
        private LocalDateTime cancellationDate;
        private LocalDateTime deliveryDate;
        private PaymentMethodOutput paymentMethod;
        private RestaurantOutput restaurant;

        private OrderOutputBuilder() {
        }

        public OrderOutputBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderOutputBuilder subtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public OrderOutputBuilder shippingFee(BigDecimal shippingFee) {
            this.shippingFee = shippingFee;
            return this;
        }

        public OrderOutputBuilder totalValue(BigDecimal totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public OrderOutputBuilder address(AddressOutput address) {
            this.address = address;
            return this;
        }

        public OrderOutputBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public OrderOutputBuilder confirmationDate(LocalDateTime confirmationDate) {
            this.confirmationDate = confirmationDate;
            return this;
        }

        public OrderOutputBuilder cancellationDate(LocalDateTime cancellationDate) {
            this.cancellationDate = cancellationDate;
            return this;
        }

        public OrderOutputBuilder deliveryDate(LocalDateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public OrderOutputBuilder paymentMethod(PaymentMethodOutput paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderOutputBuilder restaurant(RestaurantOutput restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public OrderOutput build() {
            OrderOutput orderOutput = new OrderOutput(
                    this.id,
                    this.subtotal,
                    this.shippingFee,
                    this.totalValue,
                    this.address,
                    this.creationDate,
                    this.confirmationDate,
                    this.cancellationDate,
                    this.deliveryDate,
                    this.paymentMethod,
                    this.restaurant);
            return orderOutput;
        }
    }
}
