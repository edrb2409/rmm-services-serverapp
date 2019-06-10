INSERT INTO services (name) VALUES ('DeviceManagement');


INSERT INTO services_device_type_cost(service_id, cost)
VALUES ((select id from services where name = 'DeviceManagement'), 4);
