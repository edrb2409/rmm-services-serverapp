package com.example.rmmservices.service.device;

import com.example.rmmservices.model.Device;

/**
 * Device service
 *
 * Allows to execution an device action
 */
public interface DeviceCommandService {

    /**
     * Save a device
     *
     * @param device defined
     *
     * @return device saved
     *
     * @throws com.example.rmmservices.exception.CustomerNotFoundException when customer is not found
     * @throws com.example.rmmservices.exception.DeviceTypeNotFoundException when the device type is not found
     */
    Device save(Device device);

    /**
     * Update a device
     *
     * @param id of the device
     * @param device defined
     *
     * @return device updated
     *
     * @throws com.example.rmmservices.exception.CustomerNotFoundException when customer is not found
     * @throws com.example.rmmservices.exception.DeviceTypeNotFoundException when the device type is not found
     * @throws com.example.rmmservices.exception.DeviceNotFoundException when the device is not found
     */
    Device update(Long id, Device device);

    /**
     * Delete a device
     *
     * @param customerId  customer id
     * @param deviceId of the device
     *
     * @return device deleted
     *
     * @throws com.example.rmmservices.exception.CustomerNotFoundException when customer is not found
     * @throws com.example.rmmservices.exception.DeviceNotFoundException when the device is not found
     */
    Device delete(Long customerId, Long deviceId);

}
