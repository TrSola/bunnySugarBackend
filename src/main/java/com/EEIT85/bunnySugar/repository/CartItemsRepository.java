package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {




    Optional<Object> findCartItemsByCartId(Long id);

    Optional<CartItems> findByProductsId(Long id);


    void deleteByCartId(Long userId);

    Optional<CartItems> findByIdAndCart_Users_Id(Long itemId, Long userId);


    @Query("SELECT new com.EEIT85.bunnySugar.dto.cart.CartSelectDto(ci.id, pd" +
            ".price, ci.quantity, p.productName, pd.imageUrl) " +
            "FROM CartItems ci " +
            "JOIN ci.cart c " +
            "JOIN ci.products p " +
            "JOIN p.productDetails pd " +
            "WHERE c.users.id = :usersId")
    List<CartSelectDto> findCartItemsByUsersId(@Param("usersId") Long usersId);


}
