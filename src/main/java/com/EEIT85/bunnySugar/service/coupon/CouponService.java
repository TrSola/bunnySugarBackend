package com.EEIT85.bunnySugar.service.coupon;

import com.EEIT85.bunnySugar.dto.coupon.CouponInsertDto;
import com.EEIT85.bunnySugar.entity.Coupon;
import com.EEIT85.bunnySugar.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    public void insertCoupon(CouponInsertDto couponInsertDto) {
        Coupon coupon = new Coupon(couponInsertDto.getCouponName(),
                couponInsertDto.getDiscountNumber(), couponInsertDto.getEndDate(),
                couponInsertDto.getEnable(), LocalDateTime.now(), LocalDateTime.now());
        couponRepository.save(coupon);
    }
}
