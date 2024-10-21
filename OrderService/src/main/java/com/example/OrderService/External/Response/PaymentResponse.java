package com.example.OrderService.External.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long paymentId;
    private String paymentStatus;
    private Long amount;
    private String paymentMode;
    private Instant paymentDate ;
    private Long  orderId ;
}
