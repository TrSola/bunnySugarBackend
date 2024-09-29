package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrderDetailsAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersFullInfoAdminDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersInfoAdminDto;
import com.EEIT85.bunnySugar.entity.Orders;
import com.EEIT85.bunnySugar.entity.PaymentDetails;
import com.EEIT85.bunnySugar.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrdersAdminService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    // 查詢所有訂單，並返回分頁的訂單資訊
    public Page<OrdersInfoAdminDto> getAllOrders(Pageable pageable) {
        System.out.println("Executing getAllOrders with pageable: " + pageable);
        // 使用Spring Data JPA分頁查詢
        Page<Object[]> rawData = ordersRepository.findAllOrdersInfo(pageable);
        System.out.println("Raw data size: " + rawData.getContent().size());
        System.out.println("Total elements: " + rawData.getTotalElements());

        // 檢查是否有資料，避免空指標異常
        if (rawData.isEmpty()) {
            // 如果沒有資料，返回空的分頁結果
            return Page.empty(pageable);
        }

        List<OrdersInfoAdminDto> dtoList = new ArrayList<>();

        // 將查詢出的原始資料封裝為DTO
        for (Object[] row : rawData) {
            // 檢查 row 的長度和內容是否正確，避免轉換錯誤
            if (row != null && row.length >= 8) {
                OrdersInfoAdminDto ordersInfoAdminDto = new OrdersInfoAdminDto();
                ordersInfoAdminDto.setOrderNumber((String) row[0]);
                ordersInfoAdminDto.setUserName((String) row[1]);
                ordersInfoAdminDto.setUserPhone((String) row[2]);
                ordersInfoAdminDto.setUserEmail((String) row[3]);
                ordersInfoAdminDto.setPaidPrice((Integer) row[4]);
                ordersInfoAdminDto.setPaymentStatus((String) row[5]);

                // 處理 orderDetails
                OrderDetailsAdminDto orderDetailsDto = new OrderDetailsAdminDto();
                orderDetailsDto.setProductName((String) row[6]);
                orderDetailsDto.setQuantity((Integer) row[7]);

                // 添加到 details 列表
                List<OrderDetailsAdminDto> detailsList = new ArrayList<>();
                detailsList.add(orderDetailsDto);

                ordersInfoAdminDto.setOrderDetails(detailsList);

                // 添加到 DTO 列表
                dtoList.add(ordersInfoAdminDto);
            } else {
                System.out.println("Invalid row data: " + Arrays.toString(row));
            }
        }

        // 返回分頁結果
        return new PageImpl<>(dtoList, pageable, rawData.getTotalElements());
    }

    // 根據會員電話查詢訂單，並返回分頁的訂單資訊
    public Page<OrdersInfoAdminDto> getOrdersByUserPhone(String phone, Pageable pageable) {
        // 使用Spring Data JPA分頁查詢
        Page<Object[]> rawData = ordersRepository.findOrdersByUserPhone(phone, pageable);

        List<OrdersInfoAdminDto> dtoList = new ArrayList<>();

        // 將查詢出的原始資料封裝為DTO
        for (Object[] row : rawData) {
            OrdersInfoAdminDto ordersInfoAdminDto = new OrdersInfoAdminDto();
            ordersInfoAdminDto.setOrderNumber((String) row[0]);
            ordersInfoAdminDto.setUserName((String) row[1]);
            ordersInfoAdminDto.setUserPhone((String) row[2]);
            ordersInfoAdminDto.setUserEmail((String) row[3]);
            ordersInfoAdminDto.setPaidPrice((Integer) row[4]);
            ordersInfoAdminDto.setPaymentStatus((String) row[5]);

            OrderDetailsAdminDto orderDetailsDto = new OrderDetailsAdminDto();
            orderDetailsDto.setProductName((String) row[6]);
            orderDetailsDto.setQuantity((Integer) row[7]);

            List<OrderDetailsAdminDto> detailsList = new ArrayList<>();
            detailsList.add(orderDetailsDto);

            ordersInfoAdminDto.setOrderDetails(detailsList);

            dtoList.add(ordersInfoAdminDto);
        }

        // 返回分頁結果
        return new PageImpl<>(dtoList, pageable, rawData.getTotalElements());
    }

    // 根據訂單ID查詢訂單的詳細信息，並進行分頁
    public OrdersFullInfoAdminDto getOrderFullInfoByOrderId(Long orderId, Pageable pageable) {
        // 使用Spring Data JPA分頁查詢
        Page<Object[]> rawData = ordersRepository.findOrderFullInfoByOrderId(orderId, pageable);

        if (rawData.isEmpty()) {
            return null; // 或者拋出自定義異常
        }

        // 將查詢結果封裝為 OrdersFullInfoAdminDto
        OrdersFullInfoAdminDto ordersFullInfoAdminDto = new OrdersFullInfoAdminDto();

        // 假設第一筆資料包含訂單的基本信息
        Object[] firstRow = rawData.getContent().get(0);
        ordersFullInfoAdminDto.setOrderNumber((String) firstRow[0]);
        ordersFullInfoAdminDto.setUserName((String) firstRow[1]);
        ordersFullInfoAdminDto.setUserPhone((String) firstRow[2]);
        ordersFullInfoAdminDto.setUserEmail((String) firstRow[3]);
        ordersFullInfoAdminDto.setCreateTime((LocalDateTime) firstRow[4]);
        ordersFullInfoAdminDto.setPaymentStatus((String) firstRow[5]);
        ordersFullInfoAdminDto.setPickupStatus((String) firstRow[6]);
        ordersFullInfoAdminDto.setPaidPrice((Integer) firstRow[7]);
        ordersFullInfoAdminDto.setPaymentMethod((String) firstRow[8]);
        ordersFullInfoAdminDto.setPaymentDate((LocalDateTime) firstRow[9]);
        ordersFullInfoAdminDto.setTotal((Integer) firstRow[10]);
        ordersFullInfoAdminDto.setCouponName((String) firstRow[11]);
        ordersFullInfoAdminDto.setUsedBunnyCoins((Integer) firstRow[12]);
        ordersFullInfoAdminDto.setPickupTime((LocalDateTime) firstRow[13]);

        // 封裝訂單詳細信息
        List<OrderDetailsAdminDto> orderDetailsList = new ArrayList<>();
        for (Object[] row : rawData.getContent()) {
            OrderDetailsAdminDto orderDetailsDto = new OrderDetailsAdminDto();
            orderDetailsDto.setProductName((String) row[14]);
            orderDetailsDto.setQuantity((Integer) row[15]);
            orderDetailsDto.setPrice((Integer) row[16]);
            orderDetailsList.add(orderDetailsDto);
        }

        ordersFullInfoAdminDto.setOrderDetails(orderDetailsList);
        return ordersFullInfoAdminDto;
    }

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
