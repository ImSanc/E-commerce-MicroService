package com.example.OrderService.Service;

import com.example.OrderService.Model.OrderRequest;
import com.example.OrderService.Model.OrderResponse;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderid);
}
