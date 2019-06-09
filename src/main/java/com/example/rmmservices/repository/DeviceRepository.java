package com.example.rmmservices.repository;

import com.example.rmmservices.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByIdAndCustomer_Id(Long id, Long customerId);

    List<Device> findAllByCustomer_Id(Long customerId);

}
