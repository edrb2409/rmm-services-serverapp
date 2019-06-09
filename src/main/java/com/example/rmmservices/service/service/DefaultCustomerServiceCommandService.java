package com.example.rmmservices.service.service;

import com.example.rmmservices.exception.CustomerNotFoundException;
import com.example.rmmservices.exception.NotUniqueServiceException;
import com.example.rmmservices.exception.ServiceNotFoundException;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.CustomerServiceRepository;
import com.example.rmmservices.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Slf4j
@Service
public class DefaultCustomerServiceCommandService implements CustomerServiceCommandService {

    private final CustomerServiceRepository customerServiceRepository;

    private final CustomerRepository customerRepository;

    private final ServiceRepository serviceRepository;

    public DefaultCustomerServiceCommandService(CustomerServiceRepository customerServiceRepository,
                                                CustomerRepository customerRepository,
                                                ServiceRepository serviceRepository) {
        this.customerServiceRepository = customerServiceRepository;
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public CustomerService addService(Long customerId, Long serviceId) {
        log.info("Adding a service {} into customer {}", serviceId, customerId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        com.example.rmmservices.model.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(ServiceNotFoundException::new);

        try {
            return customerServiceRepository.save(CustomerService.builder()
                    .service(service)
                    .customer(customer)
                    .build());
        } catch (PersistenceException e) {
            throw new NotUniqueServiceException();
        }
    }

    @Override
    public void deleteService(Long customerId, Long serviceId) {
        log.info("Removing a service {} from customer {}", serviceId, customerId);

        customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        serviceRepository.findById(serviceId)
                .orElseThrow(ServiceNotFoundException::new);

        customerServiceRepository.findByCustomer_IdAndAndService_Id(customerId, serviceId)
                .ifPresent(it -> customerServiceRepository.deleteById(it.getId()));
    }
}
