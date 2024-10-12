package com.EEIT85.bunnySugar.controller.wishList;


import com.EEIT85.bunnySugar.dto.wishList.WishListInsertDto;
import com.EEIT85.bunnySugar.dto.wishList.WishListItemDto;
import com.EEIT85.bunnySugar.entity.WishListItems;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.service.wishListService.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishList")
public class WishListController {

    @Autowired
    WishListService wishListService;

    // 查找用戶收藏清單，支持分頁
    @GetMapping("/items/{userId}")
    public ResponseEntity<Page<WishListItemDto>> getWishListItemsByUserId(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Long userId = (Long) request.getAttribute("userId");
        Pageable pageable = PageRequest.of(page, size);
        Page<WishListItemDto> wishListItems = wishListService.getWishListItemsByUserId(userId, pageable);

        if (wishListItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(wishListItems);
    }

    // 新增商品到收藏清單
    @PostMapping("/add") // 定義 POST 請求的端點
    public ResponseEntity<String> addProductToWishList(
            @RequestBody WishListInsertDto wishListInsertDto,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            wishListService.addProductToWishList(wishListInsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("商品已成功新增到收藏清單");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public ResponseEntity<Void> deleteProductFromWishList(
            HttpServletRequest request, @PathVariable Long productId) {

        Long userId = (Long) request.getAttribute("userId");
        wishListService.deleteProductFromWishList(userId, productId);

        return ResponseEntity.noContent().build();
    }
}