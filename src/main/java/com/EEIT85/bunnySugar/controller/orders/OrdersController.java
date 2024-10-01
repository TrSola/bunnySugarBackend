package com.EEIT85.bunnySugar.controller.orders;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.service.orders.admin.OrdersAdminService;
import com.EEIT85.bunnySugar.service.orders.front.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {
    Long userId = 1L;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersAdminService ordersAdminService;


    @PostMapping
    public ResponseEntity<String> addToOrders(HttpServletRequest request, @RequestBody OrdersInsertDto ordersInsertDto) {
        Long userId = (Long) request.getAttribute("userId");
        ordersService.insertOrder(ordersInsertDto, userId);
        return ResponseEntity.ok("成功新增訂單");
    }

    @GetMapping
    public ResponseEntity<Page<OrdersInfoAdminDto>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,   // 當前頁碼，預設為第1頁（頁碼從1開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，預設為10條
    ) {
        System.out.println("Received request for page " + page + " with size " + size);
        // PageRequest.of的頁碼從0開始，所以這裡需要減去1
        Pageable pageable = PageRequest.of(page - 1, size);

        // 調用服務層方法獲取分頁的訂單資料
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getAllOrders(pageable);
        System.out.println("Returned page size: " + ordersPage.getContent().size());

        return ResponseEntity.ok(ordersPage);
    }

    @PostMapping("/admin/byPhone")
    public ResponseEntity<Page<OrdersInfoAdminDto>> getOrdersByUserPhone(
            @RequestParam String phone,                    // 會員電話
            @RequestParam(defaultValue = "1") int page,    // 當前頁碼，預設為第1頁
            @RequestParam(defaultValue = "10") int size    // 每頁顯示的資料數量，預設為10條
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrdersInfoAdminDto> ordersPage = ordersAdminService.getOrdersByUserPhone(phone, pageable);
        return ResponseEntity.ok(ordersPage);
    }

    @PostMapping("/admin/details")
    public ResponseEntity<OrdersFullInfoAdminDto> getOrderDetailsByOrderId(
            @RequestParam Long orderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        OrdersFullInfoAdminDto orderDetails = ordersAdminService.getOrderFullInfoByOrderId(orderId, pageable);
        if (orderDetails == null) {
            return ResponseEntity.notFound().build(); // 查無資料返回404
        }
        return ResponseEntity.ok(orderDetails);
    }


    // 更新取貨或付款狀態
    @PostMapping("/admin/{orderId}/updateStatus")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody OrdersAdminUpdateDto dto) {
        ordersAdminService.updateOrderStatus(orderId, dto);
        return ResponseEntity.ok("訂單狀態更新成功");
    }

    // 前台根據訂單編號查詢訂單及細節
    @PostMapping("/details/{orderNumber}")
    public ResponseEntity<OrdersFrontDto> getOrderByOrderNumber(@PathVariable String orderNumber) {
        OrdersFrontDto order = ordersService.getOrderByOrderNumber(orderNumber);

        if (order == null) {
            return ResponseEntity.notFound().build(); // 查無訂單時返回404
        }

        return ResponseEntity.ok(order);
    }
}
