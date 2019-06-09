package com.example.rmmservices.service.service;

import com.example.rmmservices.model.Service;

import java.util.List;

/**
 * Manages queries for customer services
 *
 */
public interface CustomerServiceQueryService {

    /**
     * find a list of services for that customer
     *
     * @param customerId customer id
     *
     * @return list of services associated
     */
    List<Service> findServicesFor(Long customerId);

}
