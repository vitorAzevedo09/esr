CREATE TABLE restaurants (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cuisine_id BIGINT NOT NULL,
    name VARCHAR(80) NOT NULL,
    delivery_fee DECIMAL(10,2) NOT NULL,
    updated_at DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    
    address_city_id BIGINT,
    address_zip_code VARCHAR(9),
    address_street VARCHAR(100),
    address_number VARCHAR(20),
    address_complement VARCHAR(60),
    address_neighborhood VARCHAR(60),
    
    PRIMARY KEY (id),
    CONSTRAINT fk_restaurant_cuisine
    FOREIGN KEY (cuisine_id) REFERENCES cuisines (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
