package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesReportRepository extends JpaRepository<Orders, Long> {

    // 所有商品數據
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto(p.id, p.productName, SUM(od.price), SUM(od.quantity), " +
            "CAST((SELECT SUM(od2.price * od2.quantity) FROM OrderDetails od2) AS long)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "GROUP BY p.id, p.productName")
    List<SalesReportDto> findSalesReport();

    // 特定區間的銷售狀況
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto(p.id, p.productName, SUM(od.quantity), SUM(od.price), " +
            "CAST((SELECT SUM(od2.price * od2.quantity) FROM OrderDetails od2 JOIN od2.orders o2 WHERE o2.createTime BETWEEN :startTime AND :endTime) AS long)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "JOIN od.orders o " +
            "WHERE o.createTime BETWEEN :startTime AND :endTime " +
            "GROUP BY p.id, p.productName")
    List<SalesReportDto> findSalesReportByTimeRange(@Param("startTime") LocalDateTime startTime,
                                                                    @Param("endTime") LocalDateTime endTime);



}

