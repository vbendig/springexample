use product_db;
insert into purchase_order (creation_date) values ('2023-01-01T00:00:00.00-06:00');
insert into purchase_order (creation_date) values ('2023-01-01T00:00:00.00-06:00');
insert into purchase_order (creation_date) values ('2023-01-01T00:00:00.00-06:00');
insert into purchase_order (creation_date) values ('2023-01-01T00:00:00.00-06:00');

insert into product (name, description) values ('papa', 'Papa amarilla');
insert into product (name, description) values ('cebolla blanca', 'Cebolla blanca');
insert into product (name, description) values ('zanahoria', 'Zanahoria roja');
insert into product (name, description) values ('chile', 'Chile rojo');
insert into product (name, description) values ('aguacate', 'Aguacate Hass');

insert into purchase_order_product (purchase_order_id, product_id) values (1, 1);
insert into purchase_order_product (purchase_order_id, product_id) values (1, 2);
insert into purchase_order_product (purchase_order_id, product_id) values (2, 3);
insert into purchase_order_product (purchase_order_id, product_id) values (2, 4);
insert into purchase_order_product (purchase_order_id, product_id) values (3, 5);
insert into purchase_order_product (purchase_order_id, product_id) values (4, 5);