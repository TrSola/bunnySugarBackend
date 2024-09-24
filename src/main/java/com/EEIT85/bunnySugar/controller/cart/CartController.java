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
    Long userId = 1L; // 假設用戶 ID 固定為 1，實際情況應根據需求調整

    @Autowired
    CartService cartService;

//    @GetMapping("/{userId}")
//    public ResponseEntity<List<CartSelectDto>> getCartItems(@PathVariable Long userId) {
//        List<CartSelectDto> cartItems = cartService.getCartItemsByUserId(userId);
//        return ResponseEntity.ok(cartItems);
//    }

    // 新增購物車
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartInsertDto cartInsertDto) {
        cartService.insertCart(cartInsertDto);
        return ResponseEntity.ok("成功新增購物車");
    }

    // 刪除購物車
//    @DeleteMapping("/{usersId}/{id}")
//    public ResponseEntity<String> deleteCart(@PathVariable Long userId ,
//                                             @PathVariable Long id) {
//        cartService.deleteCartItem(id, userId);
//        return ResponseEntity.ok("成功刪除單個購物車品項");
//    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteAllCartItems(@PathVariable Long userId) {
//        cartService.deleteAllCartItems(userId);
//        return ResponseEntity.ok("成功清空購物車");
//    }
}


//
//    // 更新購物車
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateCart(@PathVariable Long id, @RequestBody CartUpdateDto cartUpdateDto) {
//        cartService.updateCart(id, cartUpdateDto);
//        return ResponseEntity.ok("成功更新購物車");
//    }

