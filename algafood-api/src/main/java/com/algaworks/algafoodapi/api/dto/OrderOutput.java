package com.algaworks.algafoodapi.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algafoodapi.domain.model.OrderStatus;

/**
 * OrderOutput
 */
public record OrderOutput(
        Long id,
        BigDecimal subtotal,
        BigDecimal shippingFee,
        BigDecimal totalValue,
        OrderStatus status,
        AddressOutput address,
        OffsetDateTime creationDate,
        OffsetDateTime confirmationDate,
        OffsetDateTime cancellationDate,
        OffsetDateTime deliveryDate,
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
        OrderStatus status;
        private AddressOutput address;
        private OffsetDateTime creationDate;
        private OffsetDateTime confirmationDate;
        private OffsetDateTime cancellationDate;
        private OffsetDateTime deliveryDate;
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

        public OrderOutputBuilder creationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public OrderOutputBuilder confirmationDate(OffsetDateTime confirmationDate) {
            this.confirmationDate = confirmationDate;
            return this;
        }

        public OrderOutputBuilder cancellationDate(OffsetDateTime cancellationDate) {
            this.cancellationDate = cancellationDate;
            return this;
        }

        public OrderOutputBuilder deliveryDate(OffsetDateTime deliveryDate) {
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

        public OrderOutputBuilder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderOutput build() {
            OrderOutput orderOutput = new OrderOutput(
                    this.id,
                    this.subtotal,
                    this.shippingFee,
                    this.totalValue,
                    this.status,
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
