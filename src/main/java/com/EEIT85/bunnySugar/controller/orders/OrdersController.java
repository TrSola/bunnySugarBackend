package com.EEIT85.bunnySugar.controller.orders;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.service.orders.admin.OrdersAdminService;
import com.EEIT85.bunnySugar.service.orders.front.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {
    Long userId = 1L;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersAdminService ordersAdminService;


    @PostMapping("/{userId}")
    public ResponseEntity<String> addToOrders(@RequestBody OrdersInsertDto ordersInsertDto, @PathVariable Long userId) {
        ordersService.insertOrder(ordersInsertDto, userId);
        return ResponseEntity.ok("成功新增訂單");
    }

    @GetMapping
    public ResponseEntity<Page<OrdersInfoAdminDto>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,   // 當前頁碼，預設為第1頁（頁碼從1開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，預設為10條
    ) {
        System.out.println("Received request for page " + page + " with size " + size);
        // Pageable類型，PageRequest.of的頁碼從0開始，所以這裡需要減去1
        Pageable pageable = PageRequest.of(page - 1, size);

        // 調用服務層方法獲取分頁的訂單資料
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getAllOrders(pageable);
        System.out.println("Returned page size: " + ordersPage.getContent().size());

        // 直接返回 Page<OrdersInfoAdminDto>，保持 Spring Data Web 的返回格式
        return ResponseEntity.ok(ordersPage);
    }

    @PostMapping("/by-phone")
    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByUserPhone(
            @RequestParam String phone,                    // 會員電話
            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
    ) {
        // Pageable類型，PageRequest.of的頁碼從0開始，所以這裡需要減去1
        Pageable pageable = PageRequest.of(page - 1, size);

        // 調用服務層方法根據會員電話獲取分頁的訂單資料
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(phone, pageable);

        // 返回HTTP狀態為200的ResponseEntity，並將查詢結果作為返回數據
        return ResponseEntity.ok(ordersPage);
    }

    @PostMapping("/details")
    public ResponseEntity<OrdersFullInfoAdminDto> getOrderDetailsByOrderId(
            @RequestParam Long orderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Pageable 分頁參數
        Pageable pageable = PageRequest.of(page - 1, size);

        OrdersFullInfoAdminDto orderDetails = ordersAdminService.getOrderFullInfoByOrderId(orderId, pageable);

        if (orderDetails == null) {
            return ResponseEntity.notFound().build(); // 查無資料返回404
        }

        return ResponseEntity.ok(orderDetails);
    }

}
