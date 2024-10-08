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

}
