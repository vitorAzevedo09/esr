-- Temporarily disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Delete data from various tables
DELETE FROM city;
DELETE FROM cuisine;
DELETE FROM state;
DELETE FROM payment_method;
DELETE FROM group_user;
DELETE FROM group_permission;
DELETE FROM permission;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM restaurant_payment_method;
DELETE FROM user;
DELETE FROM user_group;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Reset auto-increment counters for tables
ALTER TABLE city AUTO_INCREMENT = 1;
ALTER TABLE cuisine AUTO_INCREMENT = 1;
ALTER TABLE state AUTO_INCREMENT = 1;
ALTER TABLE payment_method AUTO_INCREMENT = 1;
ALTER TABLE group_user AUTO_INCREMENT = 1;
ALTER TABLE permission AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE restaurant AUTO_INCREMENT = 1;
ALTER TABLE user AUTO_INCREMENT = 1;

-- Insert data into "cuisine" table
INSERT INTO cuisine (id, name) VALUES
(1, 'Thai'),
(2, 'Indian'),
(3, 'Argentinian'),
(4, 'Brazilian');

-- Insert data into "state" table
INSERT INTO state (id, name) VALUES
(1, 'Minas Gerais'),
(2, 'São Paulo'),
(3, 'Ceará');

-- Insert data into "city" table
INSERT INTO city (id, name, state_id) VALUES
(1, 'Uberlândia', 1),
(2, 'Belo Horizonte', 1),
(3, 'São Paulo', 2),
(4, 'Campinas', 2),
(5, 'Fortaleza', 3);

-- Insert data into "restaurant" table
INSERT INTO restaurant (id, name, delivery_fee, cuisine_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number, address_complement, address_neighborhood) VALUES
(1, 'Thai Gourmet', 10.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', NULL),
(2, 'Thai Delivery', 9.50, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Tuk Tuk Indian Food', 15.00, 2, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Java Steakhouse', 12.00, 3, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Uncle Sam Snack Bar', 11.00, 4, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Maria Bar', 6.00, 4, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL);

-- Insert data into "payment_method" table
INSERT INTO payment_method (id, description) VALUES
(1, 'Credit Card'),
(2, 'Debit Card'),
(3, 'Cash');

-- Insert data into "permission" table
INSERT INTO permission (id, name, description) VALUES
(1, 'VIEW_KITCHENS', 'Allows viewing kitchens'),
(2, 'EDIT_KITCHENS', 'Allows editing kitchens');

-- Insert data into "restaurant_payment_method" table
INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 3),
(3, 2), (3, 3),
(4, 1), (4, 2),
(5, 1), (5, 2),
(6, 3);

-- Insert data into "product" table
INSERT INTO product (name, description, price, active, restaurant_id) VALUES
('Sweet and Sour Pork', 'Delicious pork with special sauce', 78.90, 1, 1),
('Spicy Thai Shrimp', '16 large shrimp in spicy sauce', 110.00, 1, 1),
('Spicy Salad with Grilled Meat', 'Leafy salad with thin cuts of grilled beef and our special red pepper sauce', 87.20, 1, 2),
('Garlic Naan', 'Traditional Indian bread with garlic topping', 21.00, 1, 3),
('Murg Curry', 'Chicken cubes prepared with curry sauce and spices', 43.00, 1, 3),
('Ancho Steak', 'Tender and juicy cut, two fingers thick, taken from the front of the ribeye', 79.00, 1, 4),
('T-Bone Steak', 'Very tasty cut, with a T-shaped bone, with ribeye on one side and fillet mignon on the other', 89.00, 1, 4),
('X-Tudo Sandwich', 'Big sandwich with lots of cheese, beef patty, bacon, egg, salad, and mayo', 19.00, 1, 5),
('Cupim Skewer', 'Served with flour, cassava, and vinaigrette', 8.00, 1, 6);

-- Insert data "user" table
INSERT INTO user (id, name, email, password, register_at) VALUES
(1, 'João da Silva', 'joao.ger@algafood.com', '123', UTC_TIMESTAMP),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', UTC_TIMESTAMP),
(3, 'José Souza', 'jose.aux@algafood.com', '123', UTC_TIMESTAMP),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', UTC_TIMESTAMP);

