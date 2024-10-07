package com.example.OrderService.Model;

import com.example.OrderService.Constants.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long productId;
    private Long totalAmount;
    private Long quantity;
    private PaymentMode paymentMode;
}
