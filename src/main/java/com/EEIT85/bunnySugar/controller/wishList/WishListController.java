package com.EEIT85.bunnySugar.controller.wishList;


import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.service.wishListService.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishList")
public class WishListController {

    @Autowired
    WishListService wishListService;

//    @GetMapping("/wishlist")
//    public Page<WishList> getUserWishList(
//            @RequestParam Long userId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return wishListService.getUserWishListPaginated(userId, pageable);
//    }

    // 查詢收藏清單
    @GetMapping("/wishlist")
    public ResponseEntity<?> getUserWishList(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<WishList> wishListPage = wishListService.getUserWishListPaginated(userId, pageable);

        if (wishListPage.isEmpty()) {
            return ResponseEntity.ok("empty");
        }

        return ResponseEntity.ok(wishListPage);
    }

    // 新增收藏
//    @PostMapping("/add")
//    public ResponseEntity<WishList> addProductToWishList(
//            @RequestParam Long userId,
//            @RequestParam Long productId) {
//        WishList wishList = wishListService.addProductToWishList(userId, productId);
//        return ResponseEntity.ok(wishList);
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<String> removeProductFromWishList(
//            @RequestParam Long userId,
//            @RequestParam Long productId) {
//        wishListService.removeProductFromWishList(userId, productId);
//        return ResponseEntity.ok("Product removed from wish list");
//    }


}
