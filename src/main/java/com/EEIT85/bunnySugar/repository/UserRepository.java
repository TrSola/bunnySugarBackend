package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByAccount(String account);
    Users findByVerifyingToken(String token);
    Users findByEmail(String email);  // 根據信箱查詢用戶
}

