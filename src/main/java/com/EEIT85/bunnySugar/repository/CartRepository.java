package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
