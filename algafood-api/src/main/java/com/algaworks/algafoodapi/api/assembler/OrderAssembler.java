package com.algaworks.algafoodapi.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.OrderInput;
import com.algaworks.algafoodapi.api.dto.OrderItemInput;
import com.algaworks.algafoodapi.api.dto.OrderItemOutput;
import com.algaworks.algafoodapi.api.dto.OrderOutput;
import com.algaworks.algafoodapi.domain.model.Order;
import com.algaworks.algafoodapi.domain.model.OrderItem;
import com.algaworks.algafoodapi.domain.model.Product;
import com.algaworks.algafoodapi.domain.service.ProductService;

/**
 * OrderAssembler
 */
@Component
public class OrderAssembler {

        @Autowired
        private AddressAssembler aAssembler;

        @Autowired
        private PaymentMethodAssembler pmAssembler;

        @Autowired
        private RestaurantAssembler rAssembler;

        @Autowired
        private ProductService pService;

        public OrderItemOutput toOutput(OrderItem orderItem) {
                return new OrderItemOutput(
                                orderItem.getId(),
                                orderItem.getTotalPrice(),
                                orderItem.getQuantity(),
                                orderItem.getObservation());
        }

        public OrderItem toEntity(Long restaurantID, OrderItemInput orderItemInput) {
                OrderItem o = new OrderItem();
                Product p = pService.findOrFail(restaurantID, orderItemInput.product_id());
                p.setId(orderItemInput.product_id());
                o.setProduct(p);
                o.setQuantity(orderItemInput.quantity());
                o.setObservation(orderItemInput.observation());
                return o;
        }

        public OrderOutput toOutput(Order order) {
                return OrderOutput.builder()
                                .id(order.getId())
                                .subtotal(order.getSubtotal())
                                .shippingFee(order.getShippingFee())
                                .totalValue(order.getTotalValue())
                                .creationDate(order.getCreationDate())
                                .confirmationDate(order.getConfirmationDate())
                                .cancellationDate(order.getCancellationDate())
                                .deliveryDate(order.getDeliveryDate())
                                .status(order.getStatus())
                                .address(
                                                aAssembler.toOutput(
                                                                order.getDeliveryAddress()))
                                .paymentMethod(
                                                pmAssembler.toOutput(
                                                                order.getPaymentMethod()))
                                .restaurant(
                                                rAssembler.toOutput(
                                                                order.getRestaurant()))
                                .build();
        }

        public Order toEntity(OrderInput orderInput) {
                Order o = new Order();
                o.setRestaurant(rAssembler.toEntity(orderInput.restaurant()));
                o.setDeliveryAddress(aAssembler.toEntity(orderInput.address_delivery()));
                o.setPaymentMethod(pmAssembler.toEntity(orderInput.payment_method()));
                o.setItems(orderInput.items()
                                .stream()
                                .map(oi -> toEntity(orderInput.restaurant().id(), oi))
                                .toList());
                return o;
        }
}
