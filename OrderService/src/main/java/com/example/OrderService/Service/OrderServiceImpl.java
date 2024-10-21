package com.example.OrderService.Service;

import com.example.OrderService.Constants.OrderStatus;
import com.example.OrderService.Entity.Order;
import com.example.OrderService.Exception.CustomException;
import com.example.OrderService.External.Client.PaymentService;
import com.example.OrderService.External.Client.ProductService;
import com.example.OrderService.External.Request.PaymentRequest;
import com.example.OrderService.External.Response.PaymentResponse;
import com.example.OrderService.Model.OrderRequest;
import com.example.OrderService.Model.OrderResponse;
import com.example.OrderService.Repository.OrderRepository;
import com.springboot.ProductService.Model.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("Placing the order {} ",orderRequest);

        productService.reduceQuantity( orderRequest.getProductId() , orderRequest.getQuantity());

        log.info("Creating order with status CREATED");

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .orderStatus(OrderStatus.CREATED.getStatus())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        orderRepository.save(order);

        log.info("Calling payment Service to complete the payment: ");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        OrderStatus orderStatus = OrderStatus.NONE;

        try{
            paymentService.doPayment(paymentRequest);
            log.info("payment done successfully, changing the status of order to PLACED" );
            orderStatus = OrderStatus.PLACED;
        }
        catch (Exception e){
            log.error("Error occurred in payment.Changing the order status to Failed");
            orderStatus = OrderStatus.FAILED;
        }

        if(orderStatus.getStatus().isEmpty()){
            order.setOrderStatus(orderStatus.getStatus());
            orderRepository.save(order);

        }

        log.info("Order placed with ID {} ",order.getId());
        return  order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderid) {
        log.info("Getting order by ID : {} ",orderid);

        Order order = orderRepository.findById(orderid).orElseThrow(
                ()-> new CustomException("Order not found with Order ID","NOT_FOUND",404)
        );

        log.info("invoking product service for product id : {}",order.getProductId());

        ProductResponse productResponse= restTemplate.getForObject(
                "http://PRODUCTSERVICE/product/get-products/"+order.getProductId(),
                ProductResponse.class
        );

        log.info("invoking payment service for order id : {}",order.getId() );

        PaymentResponse paymentResponse = restTemplate.getForObject(
                "http://PAYMENTSERVICE/payment/get-payment-details/"+order.getId(),
                PaymentResponse.class
        );

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();

        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getPaymentStatus())
                .build();

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}
