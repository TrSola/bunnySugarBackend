package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartId(Long id);







    @Modifying
    @Query("DELETE FROM CartItems ci WHERE ci.cart.users.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
