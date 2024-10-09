package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.wishList.WishListItemDto;
import com.EEIT85.bunnySugar.entity.WishListItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListItemsRepository extends JpaRepository<WishListItems, Long> {

//    // 查詢某用戶收藏清單中的所有商品項目
//    @Query("SELECT wi From WishListItems wi WHERE wi.wishList.users.id = :userId")
//    Page<WishListItems> findByUserId(Long userId, Pageable pageable);
//
//    // 查詢某用戶的收藏清單中是否已經包含某商品
//    @Query("SELECT wi FROM WishListItems wi WHERE wi.wishList.users.id = :userId AND wi.products.id = :productId")
//    Optional<WishListItems> findByUserIdAndProductId(Long userId, Long productId);
//
//    // 刪除某用戶收藏清單中的某商品
//    void deleteByWishListIdAndProductsId(Long wishListId, Long productId);


    // 查詢某用戶收藏清單中的所有商品項目
    @Query("SELECT new com.EEIT85.bunnySugar.dto.wishList.WishListItemDto(wi.products.id," +
            "wi.products.img1, wi.products.productName, wi.products.price)" +
            "FROM WishListItems wi WHERE wi.wishList.users.id = :userId")
    Page<WishListItemDto> findByUserId(Long userId, Pageable pageable);

    // 查詢某用戶的收藏清單中是否已經包含某商品
    @Query("SELECT wi FROM WishListItems wi WHERE wi.wishList.users.id = :userId AND" +
            "wi.products.id = :productId")
    Optional<WishListItems> findByUserIdAndProductId(Long userId, Long productId);


}
