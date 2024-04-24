CREATE TABLE orders (
id BIGSERIAL PRIMARY KEY NOT NULL,
product_id integer NOT NULL,
product_name varchar(155),
product_preco DECIMAL(10, 2) NOT NULL,
quantity int NOT NULL,
status varchar(255) NOT NULL,
created_date timestamp NOT NULL,
last_modified_date timestamp NOT NULL,
version integer NOT NULL
);