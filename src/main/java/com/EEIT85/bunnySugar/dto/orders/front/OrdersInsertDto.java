package com.EEIT85.bunnySugar.dto.orders.front;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class OrdersInsertDto {

    //payment_details
//    private LocalDateTime paymentDate; //now
    private String paymentMethod; //cart
    private Integer paymentPrice; //cart 折扣後的金額
    private String paymentStatus; //預設為未付款 查綠界訂單 完成付款後修改
    private String merchantNo; // 點擊進入綠界時取得
    //orders
//    private String orderNumber; // 要用時自己產生隨機亂碼
    private Integer total; // cart(sum) 原價
    private String couponName; // cart
    private Integer usedBunnyCoins; //cart(先判斷users的coin數量是否足夠)
    private String pickupStatus; //預設為尚未取貨
    //orderDetails
//    private Integer quantity; //cartItems
//    private Integer price; //cartItems
    private LocalDateTime pickupTime; //cart

    public OrdersInsertDto() {
    }

    public OrdersInsertDto(String paymentMethod, Integer paymentPrice, String paymentStatus, String merchantNo, Integer total, String couponName, Integer usedBunnyCoins, String pickupStatus, LocalDateTime pickupTime) {
        this.paymentMethod = paymentMethod;
        this.paymentPrice = paymentPrice;
        this.paymentStatus = paymentStatus;
        this.merchantNo = merchantNo;
        this.total = total;
        this.couponName = couponName;
        this.usedBunnyCoins = usedBunnyCoins;
        this.pickupStatus = pickupStatus;
        this.pickupTime = pickupTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Integer paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }
}
