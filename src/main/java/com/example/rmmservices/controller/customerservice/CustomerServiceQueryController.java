package com.example.rmmservices.controller.customerservice;

import com.example.rmmservices.model.Service;
import com.example.rmmservices.service.service.CustomerServiceQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers/{customerId}/services")
public class CustomerServiceQueryController {

    private final CustomerServiceQueryService customerServiceQueryService;

    public CustomerServiceQueryController(CustomerServiceQueryService customerServiceQueryService) {
        this.customerServiceQueryService = customerServiceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getServices(@PathVariable Long customerId) {
        log.info("Getting services for customer {}", customerId);

        return ResponseEntity.ok(customerServiceQueryService.findServicesFor(customerId));
    }
}
