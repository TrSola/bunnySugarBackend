package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminSelectDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto;
import com.EEIT85.bunnySugar.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

//    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto(" +
//            "u.id, u.account, u.email, u.name, u.gender, u.phone, u.birthday, u.bunnyCoin, " +
//            "u.active, u.createTime, u.updateTime, u.lastLoginTime) FROM Users u")
//    Page<OrdersAdminSelectDto> findAllOrdersAdminSelectDto(Pageable pageable);

}
