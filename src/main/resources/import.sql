insert into cafes (address, city, close_at, email, name, open_at, phone) values ('Potsdamer Str. 148', 'Berlin', '18:00:00', 'pizza@jones.com', 'Jones Pizzeria', '10:00:00', '+4912345678');
insert into cafes (address, city, close_at, email, name, open_at, phone) values ('Links Str. 2', 'Berlin', '18:00:00', 'pizza@rome.de', 'Rome', '10:00:00', '+4912345345');

INSERT INTO pizzas (name, pizza_size, key_ingredients, price, cafe_id) VALUES ('Margherita', 'S', 'Cheese, Tomato, Mozzarella, Tomato Sauce, Pizza Dough', 5.0, 1)
INSERT INTO pizzas (name, pizza_size, key_ingredients, price, cafe_id) VALUES ('Margherita', 'L', 'Cheese, Tomato, Mozzarella, Tomato Sauce, Pizza Dough', 10.0, 2)
INSERT INTO pizzas (name, pizza_size, key_ingredients, price, cafe_id) VALUES ('Four Cheese', 'M', 'Cheese, Parmesan, Gorgonzola, Mozzarella, Goat Cheese, Pizza Dough', 8.0, 1)

insert into users(username, password, role) values ('admin', 'admin', 'ROLE_ADMIN');
insert into users(username, password, role) values ('user', 'user', 'ROLE_USER');

