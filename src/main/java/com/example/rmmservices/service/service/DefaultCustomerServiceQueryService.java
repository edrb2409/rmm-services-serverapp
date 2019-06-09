package com.example.rmmservices.service.service;

import com.example.rmmservices.exception.CustomerNotFoundException;
import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.model.Service;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.CustomerServiceRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
public class DefaultCustomerServiceQueryService implements CustomerServiceQueryService {

    private final CustomerServiceRepository customerServiceRepository;

    private final CustomerRepository customerRepository;

    public DefaultCustomerServiceQueryService(CustomerServiceRepository customerServiceRepository,
                                              CustomerRepository customerRepository) {
        this.customerServiceRepository = customerServiceRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Service> findServicesFor(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        return customerServiceRepository.findByCustomer_Id(customerId)
                .stream()
                .map(CustomerService::getService)
                .collect(Collectors.toList());
    }
}
