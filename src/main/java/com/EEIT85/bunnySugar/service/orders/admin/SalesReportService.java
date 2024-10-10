package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.repository.SalesReportRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesReportService {
    @Autowired
    private SalesReportRepository salesReportRepository;

    public List<SalesReportDto> getSalesReport() {
        return salesReportRepository.findSalesReport();
    }

    public List<SalesReportDto> getSalesReportByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return salesReportRepository.findSalesReportByTimeRange(startTime, endTime);
    }
}
