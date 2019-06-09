package com.example.rmmservices.service.service;

import com.example.rmmservices.exception.CustomerNotFoundException;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.model.Service;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.CustomerServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceQueryServiceTest {

    private CustomerServiceQueryService customerServiceQueryService;

    @Mock CustomerRepository customerRepository;

    @Mock CustomerServiceRepository customerServiceRepository;

    @BeforeEach void init_() {
        customerServiceQueryService = new DefaultCustomerServiceQueryService(customerServiceRepository, customerRepository);
    }

    @Test void shouldReturnAListOfSelectedServiceByCustomer() {
        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(Customer.builder().id(1L).name("System").build()));

        when(customerServiceRepository.findByCustomer_Id(1L))
                .thenReturn(List.of(customerServiceFactory(1L, "Antivirus"),
                        customerServiceFactory(2L, "TeamViewer")));

        List<Service> servicesFound = customerServiceQueryService.findServicesFor(1L);

        assertEquals(2, servicesFound.size());

    }

    @Test void shouldRaiseAnExceptionWhenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerServiceQueryService.findServicesFor(1L));
    }

    private CustomerService customerServiceFactory(Long serviceId, String serviceName) {
        return CustomerService.builder()
                .customer(Customer.builder().id(1L).name("System").build())
                .service(Service.builder().id(serviceId).name(serviceName).build())
                .build();
    }

}
