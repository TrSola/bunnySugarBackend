package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

////    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.cart.CartSelectDto(ci.id, p.productName, ci.price, ci.quantity, pd.imageUrl) FROM Cart c " +
////            "JOIN c.cartItems ci " +
////            "JOIN ci.products p " +
////            "JOIN p.productDetails pd " +
////            "WHERE c.users.id = :userId")
//    List<CartSelectDto> findCartItemsByUserId(@Param("userId") Long userId);

    //將cart_id對應的購物車每一個清單列出後數量乘以價格並加總
    @Query("SELECT SUM(ci.quantity * ci.price) FROM CartItems ci WHERE ci.cart.id = :cartId")
    int calculateTotalPrice(@Param("cartId") Long cartId);

    Cart findByUsersId(Long userId);

}
