-- Table: public.devicetype

-- DROP TABLE devicetype;

-- devicetype_id_seq

DROP TABLE IF EXISTS devicetype;
DROP TABLE IF EXISTS devices;

CREATE TABLE device_type
(
    id serial NOT NULL,
    name text NOT NULL,
    CONSTRAINT device_type_pkey PRIMARY KEY (id)
);

CREATE TABLE devices
(
    id serial NOT NULL,
    customer_id integer NOT NULL,
    device_type_id integer not null,
    system_name text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_device_device_type FOREIGN KEY (device_type_id)
    REFERENCES device_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
