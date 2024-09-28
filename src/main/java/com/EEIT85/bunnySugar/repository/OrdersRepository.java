package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o.orderNumber, u.name, u.phone, u.email, o.paymentPrice, "
            + "pd.paymentStatus, od.products.productName, od.quantity "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.orderDetails od "
            + "JOIN od.products p "
            + "JOIN o.paymentDetails pd")
    Page<Object[]> findAllOrdersInfo(Pageable pageable);

    @Query("SELECT o.orderNumber, u.name, u.phone, u.email, o.paymentPrice, "
            + "pd.paymentStatus, od.products.productName, od.quantity "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.orderDetails od "
            + "JOIN od.products p "
            + "JOIN o.paymentDetails pd "
            + "WHERE u.phone = :phone")  // 根據會員電話查詢
    Page<Object[]> findOrdersByUserPhone(@Param("phone") String phone, Pageable pageable);

    // 根據訂單ID查詢訂單的詳細信息
    @Query("SELECT o.orderNumber, u.name, u.phone, u.email, o.createTime, pd.paymentStatus, o.pickupStatus, "
            + "o.paymentPrice, pd.paymentMethod, pd.paymentDate, o.total, o.couponName, o.usedBunnyCoins, "
            + "o.pickupTime, od.products.productName, od.quantity, od.price "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.orderDetails od "
            + "JOIN o.paymentDetails pd "
            + "WHERE o.id = :id")
    Page<Object[]> findOrderFullInfoByOrderId(@Param("id") Long id, Pageable pageable);
}
