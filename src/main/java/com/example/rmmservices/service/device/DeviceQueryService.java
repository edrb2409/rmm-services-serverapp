package com.example.rmmservices.service.device;

import com.example.rmmservices.model.Device;

import java.util.List;

/**
 * Executes queries for devices
 */
public interface DeviceQueryService {

    /**
     * Find a device for its id
     *
     * @param id device id
     *
     * @return device found
     *
     * @throws com.example.rmmservices.exception.DeviceNotFoundException when the device is not found
     */
    Device findBy(Long id);

    /**
     * Find all devices
     *
     * @return list of devices
     */
    List<Device> findAll();

}
