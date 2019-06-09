package com.example.rmmservices.service.device;

import com.example.rmmservices.model.Device;

import java.util.List;

/**
 * Executes queries for devices
 */
public interface DeviceQueryService {

    /**
     * Find a device for its id and customer
     *
     * @param customerId  customer id
     * @param deviceId device id
     *
     * @return device found
     *
     * @throws com.example.rmmservices.exception.DeviceNotFoundException when the device is not found
     */
    Device findBy(Long customerId, Long deviceId);

    /**
     * Find all devices for a customer
     *
     * @param customerId customer id
     *
     * @return list of devices
     */
    List<Device> findAll(Long customerId);

}
