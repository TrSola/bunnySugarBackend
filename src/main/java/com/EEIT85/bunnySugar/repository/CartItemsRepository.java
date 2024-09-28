package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {




    Optional<Object> findCartItemsByCartId(Long id);

    Optional<CartItems> findByProductsId(Long id);


    void deleteByCartId(Long userId);

    Optional<CartItems> findByIdAndCart_Users_Id(Long itemId, Long userId);

    @Query("SELECT new com.EEIT85.bunnySugar.dto.cart.CartSelectDto(ci.id, pd.price, " +
            "ci.quantity, p.productName, pd.imageUrl, p.stocks, u.bunnyCoin, u.userVip) " +
            "FROM CartItems ci " +
            "JOIN ci.cart c " +
            "JOIN ci.products p " +
            "JOIN p.productDetails pd " +
            "JOIN c.users u " +
            "WHERE c.users.id = :userId")
    List<CartSelectDto> findCartItemsByUserId(@Param("userId") Long userId);


}
