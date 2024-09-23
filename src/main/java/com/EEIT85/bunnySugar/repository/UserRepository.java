package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto;
import com.EEIT85.bunnySugar.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByAccount(String account);
    Users findByVerifyingToken(String token);
    Users findByEmail(String email);  // 根據信箱查詢用戶

    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.users.MemberAdminSelectDto(" +
            "u.id, u.account, u.email, u.name, u.gender, u.phone, u.birthday, u.bunnyCoin, " +
            "u.active, u.createTime, u.updateTime, u.lastLoginTime) FROM Users u")
    Page<MemberAdminSelectDto> findAllMemberAdminSelectDto(Pageable pageable);

    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.users.MemberAdminSelectDto(" +
            "u.id, u.account, u.email, u.name, u.gender, u.phone, u.birthday, u.bunnyCoin, " +
            "u.active, u.createTime, u.updateTime, u.lastLoginTime) FROM Users u WHERE u.id = :id")
    MemberAdminSelectDto findMemberAdminSelectDtoById(@Param("id") Long id);
}

