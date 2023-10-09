-- Temporarily disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Delete data from various tables
DELETE FROM cities;
DELETE FROM cuisines;
DELETE FROM states;
DELETE FROM payment_methods;
DELETE FROM groups;
DELETE FROM group_permission;
DELETE FROM permissions;
DELETE FROM products;
DELETE FROM restaurants;
DELETE FROM restaurant_payment_method;
DELETE FROM users;
DELETE FROM user_group;
DELETE FROM restaurant_user;
DELETE FROM orders;
DELETE FROM order_items;


-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Reset auto-increment counters for tables
ALTER TABLE cities AUTO_INCREMENT = 1;
ALTER TABLE cuisines AUTO_INCREMENT = 1;
ALTER TABLE states AUTO_INCREMENT = 1;
ALTER TABLE payment_methods AUTO_INCREMENT = 1;
ALTER TABLE groups AUTO_INCREMENT = 1;
ALTER TABLE permissions AUTO_INCREMENT = 1;
ALTER TABLE products AUTO_INCREMENT = 1;
ALTER TABLE restaurants AUTO_INCREMENT = 1;
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE user_group AUTO_INCREMENT = 1;
ALTER TABLE restaurant_user AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;
ALTER TABLE order_items AUTO_INCREMENT = 1;

-- Insert data into "cuisines" table
INSERT INTO cuisines (id, name) VALUES
(1, 'Thai'),
(2, 'Indian'),
(3, 'Argentinian'),
(4, 'Brazilian');

-- Insert data into "states" table
INSERT INTO states (id, name) VALUES
(1, 'Minas Gerais'),
(2, 'São Paulo'),
(3, 'Ceará');

-- Insert data into "cities" table
INSERT INTO cities (id, name, state_id) VALUES
(1, 'Uberlândia', 1),
(2, 'Belo Horizonte', 1),
(3, 'São Paulo', 2),
(4, 'Campinas', 2),
(5, 'Fortaleza', 3);

-- Insert data into "restaurant" table
INSERT INTO restaurants (id, name, delivery_fee, cuisine_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number, address_neighborhood, address_complement) VALUES
(1, 'Thai Gourmet', 10.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', NULL),
(2, 'Thai Delivery', 9.50, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'Tuk Tuk Indian Food', 15.00, 2, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'Java Steakhouse', 12.00, 3, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Uncle Sam Snack Bar', 11.00, 4, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'Maria Bar', 6.00, 4, UTC_TIMESTAMP, UTC_TIMESTAMP, NULL, NULL, NULL, NULL, NULL, NULL);

-- Insert data into "payment_method" table
INSERT INTO payment_methods (id, description) VALUES
(1, 'Credit Card'),
(2, 'Debit Card'),
(3, 'Cash');

-- Insert data into "permission" table
INSERT INTO permissions (id, name, description) VALUES
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
INSERT INTO products (name, description, price, active, restaurant_id) VALUES
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
INSERT INTO users (id, name, email, password, register_at) VALUES
(1, 'João da Silva', 'joao.ger@algafood.com', '123', UTC_TIMESTAMP),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', UTC_TIMESTAMP),
(3, 'José Souza', 'jose.aux@algafood.com', '123', UTC_TIMESTAMP),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', UTC_TIMESTAMP);

-- insert data "groups" table
insert into groups(id, name) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

-- insert association group-permission table
insert into group_permission(group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

-- insert association user-group table
insert into user_group(user_id, group_id) values (1,1), (1,2), (2,1), (2,2);

-- insert association user-restaurant table
insert into restaurant_user(restaurant_id, user_id) values (1,1), (1,2), (2,1), (2,2);

-- Inserting a sample order
INSERT INTO orders (subtotal, shipping_fee, total_value, 
    status, creation_date, payment_method_id, restaurant_id, client_id,
    address_city_id, address_zip_code, address_street, address_number, address_neighborhood, address_complement)
VALUES (249.99, 10.00, 259.99,
    'CREATED', UTC_TIMESTAMP, 1, 1, 1,
     1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', NULL);

-- Inserting another sample order
INSERT INTO orders (subtotal, shipping_fee, total_value, 
    status, creation_date, payment_method_id, restaurant_id, client_id,
    address_city_id, address_zip_code, address_street, address_number, address_neighborhood, address_complement)
VALUES (199.99, 8.00, 207.99,
    'CREATED', UTC_TIMESTAMP, 2, 2, 2,
     2, '38400-849', 'Rua Beatriz Cristine', '1300', 'São José', NULL);

-- Inserting order items for the first order
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, observation)
VALUES (1, 1, 2, 49.99, 99.98, 'No special notes');

INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, observation)
VALUES (1, 2, 1, 99.99, 99.99, 'Extra cheese, please');

-- Inserting order items for the second order
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, observation)
VALUES (2, 2, 3, 29.99, 89.97, 'Gluten-free crust');

INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, observation)
VALUES (2, 2, 1, 49.99, 49.99, 'No onions');

