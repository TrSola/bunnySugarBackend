package com.EEIT85.bunnySugar.controller.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.service.orders.admin.OrdersAdminService;
import com.EEIT85.bunnySugar.service.orders.front.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/orders")
@RestController
public class OrdersAdminController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersAdminService ordersAdminService;

    // 查所有會員的所有訂單
    @GetMapping
    public ResponseEntity<Page<OrdersInfoAdminDto>> getAllOrders(
            HttpServletRequest request,
            @RequestParam(defaultValue = "1") int page,   // 當前頁碼，預設為第1頁（頁碼從1開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，預設為10條
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        // PageRequest.of的頁碼從0開始，所以這裡需要減去1
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getAllOrdersInfo(pageable);
        return ResponseEntity.ok(ordersPage);
    }

    // 根據電話號碼查詢訂單
    @GetMapping("/byPhone")
    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByUserPhone(
            HttpServletRequest request,
            @RequestParam String phone,                    // 會員電話
            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(phone, pageable);
        return ResponseEntity.ok(ordersPage);
    }

//    @GetMapping("/details")
//    public ResponseEntity<OrdersFullInfoAdminDto> getOrderDetailsByOrderId(
//            HttpServletRequest request,
//            @RequestParam Long orderId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).build();
//        }
//
//        if (page < 1) {
//            page = 1;  // 如果頁碼小於1，則設置為1
//        }
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//        OrdersFullInfoAdminDto orderDetails = ordersAdminService.getOrderFullInfoByOrderId(orderId, pageable);
//
//        if (orderDetails == null) {
//            return ResponseEntity.notFound().build(); // 查無資料返回404
//        }
//        return ResponseEntity.ok(orderDetails);
//    }

    @GetMapping("/byOrderNumber")
    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByOrderNumber(
            HttpServletRequest request,
            @RequestParam String orderNumber,                    // 會員電話
            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
    ) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(orderNumber, pageable);
        return ResponseEntity.ok(ordersPage);
    }

    // 更新取貨或付款狀態
    @PutMapping("/{orderId}/updateStatus")
    public ResponseEntity<String> updateOrderStatus(
            HttpServletRequest request,
            @PathVariable Long orderId,
            @RequestBody OrdersAdminUpdateDto dto) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ordersAdminService.updateOrderStatus(orderId, dto);
        return ResponseEntity.ok("訂單狀態更新成功");
    }

//    // 根據訂單編號查詢訂單及細節
//    @GetMapping("/details/{orderNumber}")
//    public ResponseEntity<OrdersFrontDto> getOrderByOrderNumber(
//            HttpServletRequest request,
//            @PathVariable String orderNumber) {
//
//        Long userId = (Long) request.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).build();
//        }
//
//        OrdersFrontDto order = ordersService.getOrderByOrderNumber(orderNumber);
//
//        if (order == null) {
//            return ResponseEntity.notFound().build(); // 查無訂單時返回404
//        }
//
//        return ResponseEntity.ok(order);
//    }
}
