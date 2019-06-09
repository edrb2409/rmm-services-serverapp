-- Table: Services

DROP TABLE IF EXISTS services;

CREATE TABLE services
(
    id serial NOT NULL,
    name text NOT NULL,
    CONSTRAINT services_pkey PRIMARY KEY (id)
);

CREATE TABLE services_device_type_cost
(
    id serial NOT NULL,
    service_id integer NOT NULL,
    device_type_id integer,
    cost numeric NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sdc_services FOREIGN KEY (service_id) REFERENCES services (id),
    CONSTRAINT fk_sdc_device_type FOREIGN KEY (device_type_id) REFERENCES device_type (id)
);

INSERT INTO services (name) VALUES ('Antivirus');
INSERT INTO services (name) VALUES ('Cloudberry');
INSERT INTO services (name) VALUES ('PSA');
INSERT INTO services (name) VALUES ('TeamViewer');

INSERT INTO services_device_type_cost(service_id, device_type_id, cost)
VALUES ((select id from services where name = 'Antivirus'),
        (select id from device_type where name like 'Windows Workstation'), 5);

INSERT INTO services_device_type_cost(service_id, device_type_id, cost)
VALUES ((select id from services where name = 'Antivirus'),
        (select id from device_type where name like 'Windows Server'), 5);

INSERT INTO services_device_type_cost(service_id, device_type_id, cost)
VALUES ((select id from services where name = 'Antivirus'),
        (select id from device_type where name like 'Mac'), 7);

INSERT INTO services_device_type_cost(service_id, cost)
VALUES ((select id from services where name = 'Cloudberry'), 3);

INSERT INTO services_device_type_cost(service_id, cost)
VALUES ((select id from services where name = 'PSA'), 2);

INSERT INTO services_device_type_cost(service_id, cost)
VALUES ((select id from services where name = 'TeamViewer'), 1);



