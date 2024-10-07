package com.example.OrderService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "TORD")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "aprdid")
    private Long productId;

    @Column(name = "aprqty")
    private Long quantity;

    @Column(name = "aorddte")
    private Instant orderDate;

    @Column(name = "aordsta")
    private String orderStatus;

    @Column(name = "aordamt")
    private long amount;
}
