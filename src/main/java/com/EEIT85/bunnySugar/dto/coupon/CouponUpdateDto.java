package com.EEIT85.bunnySugar.dto.coupon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponUpdateDto extends CouponBaseDto{
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CouponUpdateDto() {
    }

    public CouponUpdateDto(String couponName, Integer discountNumber, LocalDate endDate, Boolean enable, LocalDateTime createTime, LocalDateTime updateTime) {
        super(couponName, discountNumber, endDate, enable);
        this.createTime = createTime;
        this.updateTime = updateTime;
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
}
