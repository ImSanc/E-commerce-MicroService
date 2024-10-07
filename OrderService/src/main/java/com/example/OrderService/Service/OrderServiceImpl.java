package com.example.OrderService.Service;

import com.example.OrderService.Constants.OrderStatus;
import com.example.OrderService.Constants.PaymentMode;
import com.example.OrderService.Entity.Order;
import com.example.OrderService.Model.OrderRequest;
import com.example.OrderService.Repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("Placing the order {} ",orderRequest);
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .orderStatus(OrderStatus.CREATED.getStatus())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        orderRepository.save(order);

        log.info("Order placed with ID {} ",order.getId());
        return  order.getId();
    }
}
