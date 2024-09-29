package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o.orderNumber, u.name, u.phone, u.email, pd.paidPrice, "
            + "pd.paymentStatus, od.products.productName, od.quantity "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.orderDetails od "
            + "JOIN od.products p "
            + "JOIN o.paymentDetails pd")
    Page<Object[]> findAllOrdersInfo(Pageable pageable);

    @Query("SELECT o.orderNumber, u.name, u.phone, u.email, pd.paidPrice, "
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
            + "pd.paidPrice, pd.paymentMethod, pd.paymentDate, o.total, o.couponName, o.usedBunnyCoins, "
            + "o.pickupTime, od.products.productName, od.quantity, od.price "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.orderDetails od "
            + "JOIN o.paymentDetails pd "
            + "WHERE o.id = :id")
    Page<Object[]> findOrderFullInfoByOrderId(@Param("id") Long id, Pageable pageable);

    @Modifying
    @Query("UPDATE Orders o SET o.pickupStatus = :pickupStatus WHERE o.id = :orderId")
    int updatePickupStatus(@Param("orderId") Long orderId, @Param("pickupStatus") String pickupStatus);


    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto(o.orderNumber, o.createTime, pd.paymentStatus, o.pickupStatus, "
            + "o.pickupTime, o.total, o.couponName, o.usedBunnyCoins, pd.paidPrice, u.name, u.phone, u.email) "
            + "FROM Orders o "
            + "JOIN o.user u "
            + "JOIN o.paymentDetails pd "
            + "WHERE o.orderNumber = :orderNumber")
    OrdersFrontDto findOrderByOrderNumber(@Param("orderNumber") String orderNumber);

}
