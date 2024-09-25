package com.EEIT85.bunnySugar.controller.coupon;

import com.EEIT85.bunnySugar.dto.coupon.CouponInsertDto;
import com.EEIT85.bunnySugar.service.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/coupon")
@RestController
public class CouponAdminController {

    @Autowired
    CouponService couponService;

    @PostMapping
    public ResponseEntity<String> addNewCoupon(@RequestBody CouponInsertDto couponInsertDto) {
        couponService.insertCoupon(couponInsertDto);
        return ResponseEntity.ok("成功新增折扣碼");
    }
}
