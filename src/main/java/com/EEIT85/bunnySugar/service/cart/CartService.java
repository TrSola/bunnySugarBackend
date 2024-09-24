package com.EEIT85.bunnySugar.service.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.dto.cart.CartSelectDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.repository.CartItemsRepository;
import com.EEIT85.bunnySugar.repository.CartRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductsRepository productsRepository;

    public List<CartSelectDto> getCartByUserId() {


        String jpql = "SELECT NEW com.EEIT85.bunnySugar.dto.cart.CartSelectDto(" +
                "CAST(pd.price * ci.quantity AS integer), " +  // total
                "ci.quantity, " +               // quantity
                "p.productName, " +             // productName
                "pd.imageUrl) " +               // imageUrl
                "FROM Cart c " +
                "JOIN c.cartItems ci " +
                "JOIN ci.products p " +
                "JOIN p.productDetails pd " +
                "WHERE c.users.id = :usersId";

        TypedQuery<CartSelectDto> query = entityManager.createQuery(jpql, CartSelectDto.class);
        query.setParameter("usersId", 1);

        return query.getResultList();
    }

    public void insertCart(CartInsertDto cartInsertDto) {

            //提出dto中的userId
            Long usersId = cartInsertDto.getUsersId();;
            //用這個id找出他在資料庫中對應的購物車
            Cart cart = cartRepository.findByUsersId(usersId);
            //修改購物車中的金額與更新時間
            cart.setTotal(cartInsertDto.getTotal());
            cart.setUpdateTime(LocalDateTime.now());
            //在資料庫中更新
            cartRepository.save(cart);
            //找出購物車的id
            Long cartId = (cart.getId());
            //新建一個購物清單實體 準備存入資料庫
            CartItems cartItems =
                    new CartItems(cart,
                            productsRepository.findByProductName(cartInsertDto.getProductName()), cartInsertDto.getQuantity(), LocalDateTime.now(), LocalDateTime.now());
            cartItemsRepository.save(cartItems);

            //如果productId存在 改為update 並增加quantity

        }



//    public void deleteCartItem(Long userId, Long itemId) {
//        // 這裡可以加上檢查，確保 itemId 屬於這個用戶
//        if (cartItemsRepository.existsByIdAndUserId(userId, itemId)) {
//            cartItemsRepository.deleteCartItemByUserId(userId, itemId);
//        } else {
//            throw new EntityNotFoundException("Item not found in cart for user id: " + userId);
//        }
//    }

    @Transactional
    public void deleteAllCartItems(Long userId) {
        cartItemsRepository.deleteByUserId(userId);
    }

}
