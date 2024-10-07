package com.example.OrderService.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("Created"),
    CANCELLED("Cancelled"),
    PAYMENT_FAILED("Payment Failed"),
    PENDING("Pending"),
    FAILED("Failed");

    private final String status;


}
