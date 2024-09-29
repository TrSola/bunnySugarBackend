package com.EEIT85.bunnySugar.controller.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.dto.cart.CartUpdateDto;
import com.EEIT85.bunnySugar.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {
    Long userId = 1L; // 假設用戶 ID 固定為 1，實際情況應根據需求調整

    @Autowired
    CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartSelectDto>> getCartItems(@PathVariable Long userId) {
        List<CartSelectDto> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }


    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartInsertDto cartInsertDto) {
        cartService.insertCart(cartInsertDto);
        return ResponseEntity.ok("成功新增購物車");
    }

    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long userId,
                                            @PathVariable Long itemId) {
        cartService.deleteCartItem(userId, itemId);
        return ResponseEntity.ok("成功刪除單一購物車品項");
    }

    @DeleteMapping("/{usersId}")
    public ResponseEntity<String> deleteAllCartItems(@PathVariable Long usersId) {
        cartService.deleteAllCartItems(usersId);
        return ResponseEntity.ok("成功清空購物車");
    }


    @PutMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody CartUpdateDto cartUpdateDto) {
        cartService.updateCartItem(cartItemId, cartUpdateDto);
        return ResponseEntity.ok("Cart item updated successfully");
    }

}





