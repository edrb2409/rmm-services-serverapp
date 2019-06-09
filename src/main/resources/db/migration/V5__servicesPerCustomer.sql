DROP TABLE IF EXISTS service_customer;

CREATE TABLE service_customer
(
    id serial NOT NULL,
    customer_id integer NOT NULL,
    service_id integer NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_sc_services FOREIGN KEY (service_id) REFERENCES services (id),
    CONSTRAINT fk_sc_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT uniq_customer_service UNIQUE (service_id, customer_id)
);
