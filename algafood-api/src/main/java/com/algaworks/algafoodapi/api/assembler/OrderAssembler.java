package com.algaworks.algafoodapi.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.OrderItemOutput;
import com.algaworks.algafoodapi.api.dto.OrderOutput;
import com.algaworks.algafoodapi.domain.model.Order;
import com.algaworks.algafoodapi.domain.model.OrderItem;

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

  public OrderItemOutput toOutput(OrderItem orderItem) {
    return new OrderItemOutput(
        orderItem.getId(),
        orderItem.getTotalPrice(),
        orderItem.getQuantity(),
        orderItem.getObservation());
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
        .address(
            aAssembler.toOutput(
                order.getDeliveryAddress()))
        .paymentMethod(
            pmAssembler.toOutput(
                order.getPaymentMethod()))
        .restaurant(
            rAssembler.toOutput(
                order.getRestaurant()))
        .items(order.getItems()
            .stream()
            .map(o -> toOutput(o))
            .toList())
        .build();
  }

}
