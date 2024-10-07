package com.EEIT85.bunnySugar.service.wishListService;

import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.repository.WishListRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsRepository productsRepository;


    public Page<WishList> getUserWishListPaginated(Long userId, Pageable pageable) {
        // 直接使用 userId 查找收藏清單
        return wishListRepository.findByUsersId(userId, pageable);
    };


    public WishList addProductToWishList(Long userId, Long productId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        WishList wishList = new WishList();
        wishList.setUsers(user);
        wishList.setProducts(product);
        wishList.setCreateTime(LocalDateTime.now());
        wishList.setUpdateTime(LocalDateTime.now());

        return wishListRepository.save(wishList);
    };



}
