package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,
        Long> {
    // 透過產品名稱進行模糊查詢
    List<Products> findByProductNameContaining(String productName);
}
