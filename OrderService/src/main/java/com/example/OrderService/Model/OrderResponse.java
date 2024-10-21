package com.example.OrderService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;
    private Instant orderDate;
    private String orderStatus;
    private Long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    @AllArgsConstructor
    public static class ProductDetails {
        private String productName;
        private Long productId;
        private Long quantity;
        private Long price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentDetails {
        private Long paymentId;
        private String paymentStatus;
        private String paymentMode;
        private Instant paymentDate ;
    }
}
