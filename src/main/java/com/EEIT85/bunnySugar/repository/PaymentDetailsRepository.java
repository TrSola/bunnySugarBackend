package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    PaymentDetails findByOrdersId(Long orderId);

    @Modifying
    @Query("UPDATE PaymentDetails pd SET pd.paymentStatus = :paymentStatus WHERE pd.orders.id = :orderId")
    int updatePaymentStatus(@Param("orderId") Long orderId, @Param("paymentStatus") String paymentStatus);



}
