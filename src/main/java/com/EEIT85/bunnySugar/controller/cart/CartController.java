package com.EEIT85.bunnySugar.controller.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.dto.cart.CartUpdateDto;
import com.EEIT85.bunnySugar.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<Page<CartSelectDto>> getCartItems(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // 將 userId 轉換為 Long
        String userIdStr = (String) request.getAttribute("userId");
        Long userId = userIdStr != null ? Long.parseLong(userIdStr) : null; // 處理可能為 null 的情況
        Page<CartSelectDto> cartItems = cartService.getCartItemsByUserId(userId, page, size);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping
    public ResponseEntity<String> addToCart(HttpServletRequest request, @RequestBody CartInsertDto cartInsertDto) {
        String userIdStr = (String) request.getAttribute("userId");
        Long userId = userIdStr != null ? Long.parseLong(userIdStr) : null;
        cartInsertDto.setUsersId(userId);
        cartService.insertCart(cartInsertDto);
        return ResponseEntity.ok("成功新增購物車");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteCartItem(HttpServletRequest request, @PathVariable Long itemId) {
        String userIdStr = (String) request.getAttribute("userId");
        Long userId = userIdStr != null ? Long.parseLong(userIdStr) : null;
        cartService.deleteCartItem(userId, itemId);
        return ResponseEntity.ok("成功刪除單一購物車品項");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCartItems(HttpServletRequest request) {
        String userIdStr = (String) request.getAttribute("userId");
        Long userId = userIdStr != null ? Long.parseLong(userIdStr) : null;
        cartService.deleteAllCartItems(userId);
        return ResponseEntity.ok("成功清空購物車");
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItem(HttpServletRequest request,
                                                 @PathVariable Long cartItemId,
                                                 @RequestBody CartUpdateDto cartUpdateDto) {
        String userIdStr = (String) request.getAttribute("userId");
        Long userId = userIdStr != null ? Long.parseLong(userIdStr) : null;
        cartService.updateCartItem(cartItemId, cartUpdateDto);
        return ResponseEntity.ok("Cart item updated successfully");
    }
}
