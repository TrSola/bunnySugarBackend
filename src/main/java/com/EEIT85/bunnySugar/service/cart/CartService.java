package com.EEIT85.bunnySugar.service.cart;

import com.EEIT85.bunnySugar.dto.cart.CartInsertDto;
import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.CartItems;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CartItemsRepository;
import com.EEIT85.bunnySugar.repository.CartRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

//    public List<CartSelectDto> getCartItemsByUserId(Long userId) {
//        System.out.println(1);
//        List<CartSelectDto> a = cartRepository.findCartItemsByUserId(userId);
//        System.out.println(a);
//        return a;
//    }
    @Transactional
    public void insertCart(CartInsertDto cartInsertDto) {


        //用dto中的userId找出他在資料庫中對應的購物車
        Cart cart = cartRepository.findByUsersId(cartInsertDto.getUsersId());
        //用dto中的productId找出對應的Product
        Products products =
                productsRepository.findById(cartInsertDto.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        //用products_id資訊查是否有這個CartItem
        Optional<CartItems> existingCartItem =
                cartItemsRepository.findByProductsId(products.getId());
        //如果有，修改後存入 若沒有．新建一個購物清單實體 準備存入資料庫
        if (existingCartItem.isPresent()) {
            CartItems cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartInsertDto.getQuantity());
            cartItem.setPrice(cartInsertDto.getPrice()); // 更新價格，如果需要的話
            cartItem.setUpdateTime(LocalDateTime.now());
            cartItemsRepository.save(cartItem);
        } else {
            // 創建新的CartItems 並用自訂建構式處理存入
            CartItems newCartItem = new CartItems(
                    cart,
                    products,
                    cartInsertDto.getQuantity(),
                    cartInsertDto.getPrice(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            cartItemsRepository.save(newCartItem);
        }
        //如果productId存在 改為update 並增加quantity

        //修改購物車中的金額與更新時間
        cart.setUpdateTime(LocalDateTime.now());
        //更新cart中的total
//        updateCartTotalAndTime(cart);
        cart.setTotal(cartRepository.calculateTotalPrice(cart.getId()));
        cartRepository.save(cart);
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
    public void deleteAllCartItems(Long usersId) {
        Cart cart = cartRepository.findByUsersId(usersId);
        cartItemsRepository.deleteByCartId(cart.getId());
        //清空購物車後把cart的total歸零
        cart.setUpdateTime(LocalDateTime.now());
        cart.setTotal(0);
        cartRepository.save(cart);
    }

}