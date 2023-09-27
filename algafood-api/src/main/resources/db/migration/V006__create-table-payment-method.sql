CREATE TABLE restaurant_payment_method (
    restaurant_id BIGINT NOT NULL,
    payment_method_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id, payment_method_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payment_methods (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE restaurant_payment_method ADD CONSTRAINT fk_rest_payment_method_payment_method
FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id);

ALTER TABLE restaurant_payment_method ADD CONSTRAINT fk_rest_payment_method_restaurant
FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);

