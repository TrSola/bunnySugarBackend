package com.EEIT85.bunnySugar.controller.orders;

import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.service.orders.front.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {
    Long userId = 1L;

    @Autowired
    OrdersService ordersService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addToOrders(@RequestBody OrdersInsertDto ordersInsertDto, @PathVariable Long userId) {
        ordersService.insertOrder(ordersInsertDto, userId);
        return ResponseEntity.ok("成功新增訂單");
    }

}
