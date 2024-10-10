package com.EEIT85.bunnySugar.controller.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.admin.SalesReportDto;
import com.EEIT85.bunnySugar.service.orders.admin.SalesReportService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/admin/sales")
@RestController
public class SalesReportController {
    @Autowired
    private SalesReportService salesReportService;

    // 取得銷售報表數據
    @GetMapping()
    public List<SalesReportDto> getSalesReport(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return salesReportService.getSalesReport();
    }

    // 指定區間銷售數據
    @GetMapping("/timeRange")
    public List<SalesReportDto> getSalesReport(HttpServletRequest request,
                                               @RequestParam("startTime") LocalDateTime startTime,
                                               @RequestParam("endTime") LocalDateTime endTime) {
        Long userId = (Long) request.getAttribute("userId");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        return salesReportService.getSalesReportByTimeRange(startTime, endTime);
    }

}
