CREATE TABLE orders (
id BIGSERIAL PRIMARY KEY NOT NULL,
order_id BIGINT NOT NULL,
product_id integer NOT NULL,
name varchar(155),
marca varchar(155),
preco DECIMAL(10, 2) NOT NULL,
status varchar(50) NOT NULL,
created_date timestamp NOT NULL,
last_modified_date timestamp NOT NULL,
version integer NOT NULL
);