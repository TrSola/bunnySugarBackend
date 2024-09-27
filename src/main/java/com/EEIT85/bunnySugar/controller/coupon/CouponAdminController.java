package com.EEIT85.bunnySugar.controller.coupon;

import com.EEIT85.bunnySugar.dto.coupon.CouponInsertDto;
import com.EEIT85.bunnySugar.dto.coupon.CouponUpdateDto;
import com.EEIT85.bunnySugar.entity.Coupon;
import com.EEIT85.bunnySugar.service.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("成功刪除折扣碼");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable Long id,
                                                @RequestBody CouponUpdateDto couponUpdateDto) {
        couponService.updateCoupon(id, couponUpdateDto);
        return ResponseEntity.ok("成功更新折扣碼");
    }

    @GetMapping()
    public List<Coupon> getCoupons() {
        return couponService.getCoupons();
    }
}