package com.EEIT85.bunnySugar.dto.orders.Admin;


public class OrdersAdminUpdateDto {
    private String pickupStatus;
    private String paymentStatus;

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
