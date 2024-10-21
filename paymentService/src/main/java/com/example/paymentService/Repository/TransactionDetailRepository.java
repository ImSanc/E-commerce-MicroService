package com.example.paymentService.Repository;

import com.example.paymentService.Entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetails,Long> {

    TransactionDetails findByOrderId(long orderId);
}
