package com.example.rmmservices.controller.customerservice;

import com.example.rmmservices.service.service.CustomerServiceCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers/{customerId}/services/{serviceId}")
public class CustomerServiceCommandController {

    private final CustomerServiceCommandService customerServiceCommandService;

    public CustomerServiceCommandController(CustomerServiceCommandService customerServiceCommandService) {
        this.customerServiceCommandService = customerServiceCommandService;
    }

    @PostMapping
    public ResponseEntity addService(@PathVariable Long customerId,
                                     @PathVariable Long serviceId) {
        log.info("Adding service {} to customer {}", serviceId, customerId);

        customerServiceCommandService.addService(customerId, serviceId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity removeService(@PathVariable Long customerId,
                                        @PathVariable Long serviceId) {
        log.info("Removing service {} from customer {}", serviceId, customerId);

        customerServiceCommandService.deleteService(customerId, serviceId);

        return ResponseEntity.ok().build();
    }
}
