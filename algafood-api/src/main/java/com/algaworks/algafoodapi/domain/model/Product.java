package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents a product offered by a restaurant.
 */
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    /**
     * Gets the unique identifier of the product.
     *
     * @return The product's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param id The product's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The product's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     *
     * @return The product's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description The product's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     *
     * @return The product's price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The product's price.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Checks if the product is active.
     *
     * @return True if the product is active; otherwise, false.
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets whether the product is active.
     *
     * @param active True if the product is active; otherwise, false.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets the restaurant that offers this product.
     *
     * @return The restaurant that offers the product.
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the restaurant that offers this product.
     *
     * @param restaurant The restaurant that offers the product.
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
