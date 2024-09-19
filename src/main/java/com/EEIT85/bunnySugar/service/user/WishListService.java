package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public Long createWishListForUserAndCart(Users user, Cart cart) {
        WishList wishList = new WishList();
        wishList.setCreateTime(LocalDateTime.now());
        wishList.setUpdateTime(LocalDateTime.now());
        wishList.setUsers(user);  // 設置 Users的Object
        wishList = wishListRepository.save(wishList);
        return wishList.getId();
    }
}
