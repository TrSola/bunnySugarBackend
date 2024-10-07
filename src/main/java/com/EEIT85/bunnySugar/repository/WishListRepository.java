package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.entity.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    // 分頁方式查詢
    Page<WishList> findByUsersId(Long userId, Pageable pageable);

    // 查詢某用戶是否已收藏某商品
    Optional<WishList> findByUsersIdAndProductsId(Long userId, Long productId);//

}
