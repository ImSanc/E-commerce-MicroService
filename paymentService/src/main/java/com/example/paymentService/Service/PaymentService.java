package com.example.paymentService.Service;

import com.example.paymentService.model.PaymentRequest;

public interface PaymentService {

    Long doPayment(PaymentRequest paymentRequest);
}
