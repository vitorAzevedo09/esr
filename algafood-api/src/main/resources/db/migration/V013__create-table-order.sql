CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subtotal DECIMAL(10, 2),
    shipping_fee DECIMAL(10, 2),
    total_value DECIMAL(10, 2),
    status VARCHAR(20),
    
    address_city_id BIGINT,
    address_zip_code VARCHAR(9),
    address_street VARCHAR(100),
    address_number VARCHAR(20),
    address_complement VARCHAR(60),
    address_neighborhood VARCHAR(60),
    
    creation_date TIMESTAMP,
    confirmation_date TIMESTAMP,
    cancellation_date TIMESTAMP,
    delivery_date TIMESTAMP,
    
    payment_method_id BIGINT,
    restaurant_id BIGINT,
    client_id BIGINT
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT,
    unit_price DECIMAL(10, 2),
    total_price DECIMAL(10, 2),
    observation TEXT
);

ALTER TABLE orders
    ADD CONSTRAINT fk_payment_method
    FOREIGN KEY (payment_method_id)
    REFERENCES payment_methods(id);

ALTER TABLE orders
    ADD CONSTRAINT fk_restaurant
    FOREIGN KEY (restaurant_id)
    REFERENCES restaurants(id);

ALTER TABLE orders
    ADD CONSTRAINT fk_client
    FOREIGN KEY (client_id)
    REFERENCES users(id);

ALTER TABLE order_items
    ADD CONSTRAINT fk_order
    FOREIGN KEY (order_id)
    REFERENCES orders(id);

