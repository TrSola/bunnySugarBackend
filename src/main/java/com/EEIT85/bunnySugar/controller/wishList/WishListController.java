package com.EEIT85.bunnySugar.controller.wishList;


import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.entity.WishListItems;
import com.EEIT85.bunnySugar.service.wishListService.WishListService;
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

    // 分頁查詢某用戶的收藏商品
    @GetMapping("/{userId}/items")
    public Page<WishListItems> getWishListItems(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return wishListService.getWishListItemsByUser(userId, page, size);
    }

    // 新增商品到收藏清單
    @PostMapping("/{userId}/items/{productId}")
    public ResponseEntity<WishListItems> addProductToWishList(@PathVariable Long userId, @PathVariable Long productId) {
        WishListItems wishListItem = wishListService.addProductToWishList(userId, productId);
        return new ResponseEntity<>(wishListItem, HttpStatus.CREATED);
    }





}
