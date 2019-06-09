package com.example.rmmservices.repository;

import com.example.rmmservices.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
