package com.example.rmmservices.repository;

import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.model.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceRepositoryTest {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Test void shouldStoreASCustomerSelectedService() {
        CustomerService customerService = CustomerService.builder()
                .customer(customer())
                .service(service(2L))
                .build();

        customerServiceRepository.save(customerService);

        assertNotNull(customerService.getId());
    }

    private Customer customer() {
        return Customer.builder()
                .id(1L)
                .build();
    }

    private Service service(Long id) {
        return Service.builder()
                .id(id)
                .build();
    }

    private CustomerService previousCustomerService() {
        return CustomerService.builder()
                .customer(customer())
                .service(service(1L))
                .build();
    }


}
