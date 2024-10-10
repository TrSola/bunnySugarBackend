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
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto(p.productName, SUM(od.quantity), SUM(od.price)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "GROUP BY p.productName")
    List<SalesReportDto> findSalesReport();

    // 指定商品銷售數據
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto(p.productName, SUM(od.quantity), SUM(od.price)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "WHERE p.productName = :productName " +
            "GROUP BY p.productName")
    List<SalesReportDto> findSalesReportByProductName(@Param("productName") String productName);

    // 特定區間的銷售狀況
    @Query("SELECT new com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto(p.productName, SUM(od.quantity), SUM(od.price)) " +
            "FROM OrderDetails od " +
            "JOIN od.products p " +
            "JOIN od.orders o " +
            "WHERE o.createTime BETWEEN :startTime AND :endTime " +
            "GROUP BY p.productName")
    List<SalesReportDto> findSalesReportByTimeRange(@Param("startTime") LocalDateTime startTime,
                                                    @Param("endTime") LocalDateTime endTime);

}
