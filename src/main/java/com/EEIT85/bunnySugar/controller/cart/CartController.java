package com.EEIT85.bunnySugar.controller.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {
    Long usersId = 1L; // 假設用戶 ID 固定為 1，實際情況應根據需求調整

    @Autowired
    CartService cartService;

    // 獲取用戶的購物車
    @GetMapping
    public List<CartSelectDto> getCartByUserId() {
        return cartService.getCartByUserId();
    }

    // 新增購物車
    @PostMapping
    public ResponseEntity<String> insertCart(@RequestBody CartInsertDto cartInsertDto) {
        cartInsertDto.setUsersId(1L);
        cartService.insertCart(cartInsertDto);
        return ResponseEntity.ok("成功新增購物車");
    }

//    // 刪除購物車
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
//        cartService.deleteCart(id);
//        return ResponseEntity.ok("成功刪除購物車");
//    }
//
//    // 更新購物車
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateCart(@PathVariable Long id, @RequestBody CartUpdateDto cartUpdateDto) {
//        cartService.updateCart(id, cartUpdateDto);
//        return ResponseEntity.ok("成功更新購物車");
//    }
}
