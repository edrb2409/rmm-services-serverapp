package com.example.rmmservices.service.service;

import com.example.rmmservices.model.CustomerService;

/**
 * Manages operations for customer and services associated
 *
 */
public interface CustomerServiceCommandService {

    /**
     * Add a service to a customer
     *
     * @param customerId customer id
     * @param serviceId service id
     */
    CustomerService addService(Long customerId, Long serviceId);

    /**
     * Delete a service from a customer
     *
     * @param customerId customer id
     * @param serviceId service id
     */
    void deleteService(Long customerId, Long serviceId);

}
