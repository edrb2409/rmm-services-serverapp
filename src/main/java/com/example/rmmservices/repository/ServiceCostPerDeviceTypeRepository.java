package com.example.rmmservices.repository;

import com.example.rmmservices.model.ServiceCostPerDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCostPerDeviceTypeRepository
        extends JpaRepository<ServiceCostPerDeviceType, Long> {
}
