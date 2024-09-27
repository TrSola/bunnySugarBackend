package com.EEIT85.bunnySugar.dto.orders.Admin;

import java.time.LocalDateTime;

public class OrderDetailsAdminUpdateDto {
    private Long id;
    private LocalDateTime pickupTime;


    public OrderDetailsAdminUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }
}


