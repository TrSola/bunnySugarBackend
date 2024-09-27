package com.EEIT85.bunnySugar.dto.orders.front;

import java.time.LocalDateTime;

public class OrdersSelectDto {
    private Integer id;
    private String orderNumber;
    private String pickupStatus;
    private Integer total; // 尚未折扣的總金額
    private String couponName; // 使用的折扣碼
    private Integer usedBunnyCoins; // 使用多少bunny coin
    private Integer paymentPrice; // 付款金額
    private LocalDateTime createTime;

    public OrdersSelectDto() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
