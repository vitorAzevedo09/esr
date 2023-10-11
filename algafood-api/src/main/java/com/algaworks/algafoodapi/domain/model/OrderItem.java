package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 */
@Entity(name = "order_items")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal unitPrice;
  private BigDecimal totalPrice;
  private Integer quantity;
  private String observation;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Order order;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Product product;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void calcUnitPrice() {
    BigDecimal unitPrice = this.getUnitPrice();
    Integer quantity = this.getQuantity();

    if (unitPrice == null) {
      unitPrice = BigDecimal.ZERO;
    }

    if (quantity == null) {
      quantity = 0;
    }

    this.setTotalPrice(unitPrice.multiply(new BigDecimal(quantity)));
  }

}
