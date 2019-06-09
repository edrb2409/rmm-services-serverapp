--Table customers

DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
    id serial NOT NULL,
    name text NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

ALTER TABLE devices
    ADD CONSTRAINT fk_device_customer FOREIGN KEY (customer_id) REFERENCES customers;

INSERT INTO customers (name) VALUES ('System Customer');


