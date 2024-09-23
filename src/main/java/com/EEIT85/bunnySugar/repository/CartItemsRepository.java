package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    CartItems findByCartId(Long id);

}
