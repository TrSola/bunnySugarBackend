package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,
        Long> {
}
