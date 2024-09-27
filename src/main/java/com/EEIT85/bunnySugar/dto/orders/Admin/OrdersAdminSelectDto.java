package com.EEIT85.bunnySugar.dto.orders.Admin;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsSelectDto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersAdminSelectDto {
    private Long id;
    private String orderNumber;
    private String pickupStatus;
    private Integer total;
    private String couponName;
    private Integer usedBunnyCoins;
    private Integer paymentPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<OrderDetailsAdminSelectDto> orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getUsedBunnyCoins() {
        return usedBunnyCoins;
    }

    public void setUsedBunnyCoins(Integer usedBunnyCoins) {
        this.usedBunnyCoins = usedBunnyCoins;
    }

    public Integer getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Integer paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderDetailsAdminSelectDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsAdminSelectDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}



