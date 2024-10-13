package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.ProductSalesDto;
import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.repository.SalesReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesReportService {

    @Autowired
    private SalesReportRepository salesReportRepository;

    // 獲取產品銷售數據和總營業額
    public SalesReportDto getSalesReport() {
        // 查詢每個產品的銷售數據
        List<ProductSalesDto> productSales = salesReportRepository.findSalesReport();

        // 查詢所有產品的總營業額
        Double totalRevenue = salesReportRepository.findTotalRevenue();

        // 組合數據並返回
        return new SalesReportDto(productSales, totalRevenue);
    }

    // 獲取特定區間的產品銷售數據和總營業額
    public SalesReportDto getSalesReportByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        // 查詢特定區間內的產品銷售數據
        List<ProductSalesDto> productSales = salesReportRepository.findSalesReportByTimeRange(startTime, endTime);

        // 查詢特定區間內所有產品的總營業額
        Double totalRevenue = salesReportRepository.findTotalRevenueByTimeRange(startTime, endTime);

        // 組合數據並返回
        return new SalesReportDto(productSales, totalRevenue);
    }
}