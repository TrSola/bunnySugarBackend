package com.EEIT85.bunnySugar.service.wishListService;

import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import com.EEIT85.bunnySugar.entity.WishListItems;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.repository.WishListItemsRepository;
import com.EEIT85.bunnySugar.repository.WishListRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    WishListItemsRepository wishListItemsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsRepository productsRepository;


//    public Page<WishList> getUserWishListPaginated(Long userId, Pageable pageable) {
//        // 直接使用 userId 查找收藏清單
//        return wishListRepository.findByUsersId(userId, pageable);
//    };

    // 分頁查詢某用戶的收藏商品
    public Page<WishListItems> getWishListItemsByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wishListItemsRepository.findByUserId(userId, pageable);
    }

    // 新增商品到收藏清單
    public WishListItems addProductToWishList (Long userId, Long productId) {

        // 查找用戶是否存在
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        // 查找商品是否存在
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        // 查找用戶的收藏清單是否存在，沒有的話創建一個新的
        WishList wishList = wishListRepository.findByUsersId(userId)
                .orElseGet(() -> {
                    WishList newWishList = new WishList();
                    newWishList.setUsers(user);
                    newWishList.setCreateTime(LocalDateTime.now());
                    newWishList.setUpdateTime(LocalDateTime.now());
                    return wishListRepository.save(newWishList);
                });

        // 檢查商品是否已經存在於該用戶的收藏清單中
        boolean productExistsInWishList = wishListItemsRepository
                .findByUserIdAndProductId(wishList.getId(), productId).isPresent();

        if (productExistsInWishList) {
            throw new RuntimeException("Product is already in the wish list");
        }

        // 新增商品到收藏清單中
        WishListItems wishListItem = new WishListItems(LocalDateTime.now(), LocalDateTime.now(), product, wishList);
        return wishListItemsRepository.save(wishListItem);

    };














//    // 新增商品到收藏清單
//    public WishList addProductToWishList(Long userId, Long productId) {
//
//        // 檢查用戶和產品是否存在
//        Users user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
//
//        Products product = productsRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
//
//        System.out.println("Adding product to wish list: userId=" + userId + ", productId=" + productId);
//
//        // 檢查產品是否已經在收藏清單中
//        if (wishListRepository.findByUsersIdAndProductsId(userId, productId).isPresent()) {
//            throw new RuntimeException("Product already in the wish list");
//        }

        // 檢查產品是否已經在收藏清單中
//        Optional<WishList> existingWishList = wishListRepository.findByUsersIdAndProductsId(userId, productId);
//        if (existingWishList.isPresent()) {
//            throw new RuntimeException("Product already in the wish list");
//        }

//        WishList wishList = new WishList();
//        wishList.setUsers(user);
////        wishList.setProducts(product);
//        wishList.setCreateTime(LocalDateTime.now());
//        wishList.setUpdateTime(LocalDateTime.now());
//
//        return wishListRepository.save(wishList);
//    };




    // 移除收藏清單中的商品
//    public void removeProductFromWishList(Long userId, Long productId) {
//        WishList wishList = wishListRepository.findByUsersIdAndProductsId(userId, productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in the wish list"));
//
//        wishListRepository.delete(wishList);
//    }


}
