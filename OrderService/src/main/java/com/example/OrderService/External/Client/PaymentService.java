package com.example.OrderService.External.Client;

import com.example.OrderService.External.Request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("paymentService/payment")
public interface PaymentService {

    @PostMapping("/pay")
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
}
