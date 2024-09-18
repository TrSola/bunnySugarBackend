package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
