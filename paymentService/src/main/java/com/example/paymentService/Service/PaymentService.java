package com.example.paymentService.Service;

import com.example.paymentService.model.PaymentRequest;
import com.example.paymentService.model.PaymentResponse;

public interface PaymentService {

    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetails(Long orderId);
}
