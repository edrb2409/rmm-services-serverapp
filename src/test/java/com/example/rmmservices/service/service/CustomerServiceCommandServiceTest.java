package com.example.rmmservices.service.service;

import com.example.rmmservices.exception.CustomerNotFoundException;
import com.example.rmmservices.exception.NotUniqueServiceException;
import com.example.rmmservices.exception.ServiceNotFoundException;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.model.Service;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.CustomerServiceRepository;
import com.example.rmmservices.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceCommandServiceTest {

    private CustomerServiceCommandService service;

    @Mock CustomerServiceRepository customerServiceRepository;

    @Mock CustomerRepository customerRepository;

    @Mock ServiceRepository serviceRepository;

    @BeforeEach void init_() {
        service = new DefaultCustomerServiceCommandService(customerServiceRepository,
                customerRepository, serviceRepository);
    }

    @Test void shouldAddAServiceIntoACustomer() {
        CustomerService customerService = CustomerService.builder()
                .service(antivirus())
                .customer(systemCustomer())
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(antivirus()));

        when(customerServiceRepository.save(customerService)).thenReturn(expectedCustomerService());

        service.addService(1L, 1L);
    }

    @Test void shouldRemoveAServiceFromACustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(antivirus()));

        when(customerServiceRepository.findByCustomer_IdAndAndService_Id(1L, 1L))
            .thenReturn(Optional.of(expectedCustomerService()));

        service.deleteService(1L, 1L);
    }

    @Test void shouldRaiseAnExceptionIfCustomerNotFoundOnAdding() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.addService(1L, 1L));

    }

    @Test void shouldRaiseAnExceptionIfServiceNotFoundOnAdding() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ServiceNotFoundException.class,
                () -> service.addService(1L, 1L));
    }

    @Test void shouldRaiseAnExceptionIfCustomerNotFoundOnRemoving() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.deleteService(1L, 1L));

    }

    @Test void shouldRaiseAnExceptionIfServiceNotFoundOnRemoving() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ServiceNotFoundException.class,
                () -> service.deleteService(1L, 1L));
    }

    @Test void shouldRaiseAnExceptionIfServiceWasAddedBefore() {
        CustomerService customerService = CustomerService.builder()
                .service(antivirus())
                .customer(systemCustomer())
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(antivirus()));

        when(customerServiceRepository.save(customerService)).thenThrow(PersistenceException.class);

        assertThrows(NotUniqueServiceException.class,
                () -> service.addService(1L, 1L));
    }

    private Customer systemCustomer() {
        return Customer.builder().id(1L).name("System").build();
    }

    private Service antivirus() {
        return Service.builder().id(1L).name("Antivirus").build();
    }

    private CustomerService expectedCustomerService() {
        return CustomerService.builder()
                .id(1L)
                .service(antivirus())
                .customer(systemCustomer())
                .build();
    }

}
