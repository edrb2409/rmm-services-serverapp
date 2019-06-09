package com.example.rmmservices.repository;

import com.example.rmmservices.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

    CustomerService removeByCustomer_IdAndAndService_Id(Long customerId, Long serviceId);

    List<CustomerService> findByCustomer_Id(Long customerId);

    Optional<CustomerService> findByCustomer_IdAndAndService_Id(Long customerId, Long serviceId);

}
