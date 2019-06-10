package com.example.rmmservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyCostDTO {

    private Long customerId;

    private BigDecimal cost;

    private LocalDateTime timestamp;
}
