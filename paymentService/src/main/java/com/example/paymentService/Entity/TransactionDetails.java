package com.example.paymentService.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Table(name = "transactiondetails")
@Data
@AllArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "aorderid")
    private long orderId;

    @Column(name = "apaymode")
    private String paymentMode;

    @Column(name = "arefnum")
    private String referenceNumber;

    @Column(name = "apaydte")
    private Instant paymentDate;

    @Column(name = "apaysta")
    private String  paymentStatus;

    @Column(name = "apayamt")
    private long amount;
}
