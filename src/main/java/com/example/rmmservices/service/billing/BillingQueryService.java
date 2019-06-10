package com.example.rmmservices.service.billing;

import java.math.BigDecimal;

public interface BillingQueryService {

    BigDecimal monthlyCost(Long customerId);

}
