package com.example.paymentService.Controller;

import com.example.paymentService.Service.PaymentService;
import com.example.paymentService.model.PaymentRequest;
import com.example.paymentService.model.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-payment-details/{id}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable(value = "id") Long orderId){
        return new ResponseEntity<>(
                paymentService.getPaymentDetails(orderId),
                HttpStatus.OK
        );
    }
}
