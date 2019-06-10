package com.example.rmmservices.controller.billing;

import com.example.rmmservices.model.dto.MonthlyCostDTO;
import com.example.rmmservices.service.billing.BillingQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/customers/{customerId}/billing")
public class MonthlyBillingQueryController {

    private final BillingQueryService billingQueryService;

    public MonthlyBillingQueryController(BillingQueryService billingQueryService) {
        this.billingQueryService = billingQueryService;
    }

    @GetMapping
    public ResponseEntity<MonthlyCostDTO> getMonthlyCost(@PathVariable Long customerId) {
        log.info("Request monthly cost for customer: {}", customerId);

        BigDecimal monthlyCost = billingQueryService.monthlyCost(customerId);

        return ResponseEntity.ok(MonthlyCostDTO.builder()
                .cost(monthlyCost)
                .customerId(customerId)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
