package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrderDetailsAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;
import com.EEIT85.bunnySugar.entity.Orders;
import com.EEIT85.bunnySugar.entity.PaymentDetails;
import com.EEIT85.bunnySugar.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersAdminService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    // 訂單後台查詢全部訂單基本資訊，(不含商品細節)
    public Page<OrdersInfoAdminDto> getAllOrdersInfo(Pageable pageable) {
        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findAllOrdersInfo(pageable);
        // 查詢訂單的商品細節
        ordersPage.forEach(order -> {
            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
            order.setOrderDetails(orderDetails);
        });
        return ordersPage;
    }

    // 訂單編號與電話號碼查詢訂單(不含商品細節)
    public Page<OrdersInfoAdminDto> searchOrders(String search, Pageable pageable) {
        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findOrdersInfoByOrderNumberOrUserPhone(search, pageable);
        // 查詢訂單的商品細節
        ordersPage.forEach(order -> {
            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
            order.setOrderDetails(orderDetails);
        });
        return ordersPage;
    }

    // 訂單後台根據會員電話查詢訂單(不含商品細節)
    public Page<OrdersInfoAdminDto> getOrdersByUserPhone(String phone, Pageable pageable) {
        Page<OrdersInfoAdminDto> ordersPage = ordersRepository.findOrdersInfoByUserPhone(phone, pageable);
        // 查詢訂單的商品細節
        ordersPage.forEach(order -> {
            List<OrderDetailsFrontDto> orderDetails = ordersRepository.findOrderDetailsByOrderNumber(order.getOrderNumber());
            order.setOrderDetails(orderDetails);
        });
        return ordersPage;
    }

    // 訂單後台根據orderNumber查詢訂單(不含商品細節)
    public OrdersInfoAdminDto getOrdersByOrderNumber(String orderNumber) {
        OrdersInfoAdminDto orderInfo = ordersRepository.findOrdersInfoByOrderNumber(orderNumber);
        return orderInfo;
    }

//    // 訂單後台編輯，跳出根據orderNumber查詢訂單的詳細資
//    public OrdersFullInfoAdminDto getOrderFullInfoByOrderNumber(String orderNumber) {
//        // 直接使用 OrdersRepository 的查詢方法
//        OrdersFullInfoAdminDto orderFullInfo = ordersRepository.findOrderFullInfoByOrderNumber(orderNumber);
//        // 查詢訂單的商品細節
//        List<OrdersFullInfoAdminDto> orderDetails = ordersRepository.findOrderFullInfoByOrderNumber(orderNumber);
//        orderFullInfo.setOrderDetails(orderDetails);
//        return orderFullInfo;
//    }

    // 更新取貨或付款狀態，保持未修改的欄位原有狀態
    @Transactional
    public void updateOrderStatus(Long orderId, OrdersAdminUpdateDto dto) {
        // 先查詢當前的取貨狀態與付款狀態
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("訂單未找到"));
        // 檢查是否需要更新取貨狀態，否則保持原狀態
        String newPickupStatus = dto.getPickupStatus() != null ? dto.getPickupStatus() : order.getPickupStatus();
        ordersRepository.updatePickupStatus(orderId, newPickupStatus);
        // 更新付款狀態，同樣保持原狀態
        PaymentDetails paymentDetails = paymentDetailsRepository.findByOrdersId(orderId);
        String newPaymentStatus = dto.getPaymentStatus() != null ? dto.getPaymentStatus() : paymentDetails.getPaymentStatus();
        paymentDetailsRepository.updatePaymentStatus(orderId, newPaymentStatus);
    }
}
