package com.EEIT85.bunnySugar.service.orders.front;

import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.entity.*;
import com.EEIT85.bunnySugar.repository.CartRepository;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void insertOrder(OrdersInsertDto ordersInsertDto, Long userId) {

        Orders orders = new Orders();
        //產生訂單編號
        String random = UUID.randomUUID().toString().replaceAll("-", "").substring(0,
                7);
        orders.setOrderNumber(random);
        //找出目前的使用者並將使用者資訊存入訂單
        Users user = userRepository.getReferenceById(userId);
        //計算本次消費獲得的coin
        Integer addCoin = ordersInsertDto.getTotal() / 500;
        user.setBunnyCoin(user.getBunnyCoin() + addCoin);
        //計算本次累積消費
        user.setAccumulateSpent(user.getAccumulateSpent() + ordersInsertDto.getTotal());
        Integer totalSpent = user.getAccumulateSpent();
        System.out.println(totalSpent);
        if(totalSpent >= 10000) {
            user.setUserVip("鑽石兔");
        }else if(totalSpent >= 6000) {
            user.setUserVip("白金兔");
        }else if(totalSpent >= 3000) {
            user.setUserVip("金兔");
        }

        orders.setUser(user);

        //初始化為List物件
        List<OrderDetails> orderDetails = new ArrayList<>();
        Cart cart = cartRepository.findByUsersId(userId);
        Set<CartItems> cartItems = cart.getCartItems();
        //cartItems有資料 一一填入orderDetails
        cartItems.forEach(cartItem -> {
            //把detail分別創建為物件新增後再一起加入orderDetails
            OrderDetails detail = new OrderDetails();
            detail.setQuantity(cartItem.getQuantity());
            detail.setPrice(cartItem.getPrice());
            detail.setCreateTime(LocalDateTime.now());
            detail.setUpdateTime(LocalDateTime.now());
            detail.setProducts(cartItem.getProducts());
            detail.setOrders(orders);
            orderDetails.add(detail);
        });
        orders.setOrderDetails(orderDetails);
        //payment部份
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setMerchantNo(random);
        paymentDetails.setCreateTime(LocalDateTime.now());
        paymentDetails.setUpdateTime(LocalDateTime.now());
        paymentDetails.setPaidPrice(ordersInsertDto.getTotal());
        paymentDetails.setPaymentMethod(ordersInsertDto.getPaymentMethod());
        paymentDetails.setPaymentDate(LocalDateTime.now());
        paymentDetails.setPaymentStatus(ordersInsertDto.getPaymentStatus());

        //建立paymentDetails與orders的關聯
        paymentDetails.setOrders(orders);
        orders.setPaymentDetails(paymentDetails);
        //orders部份
        orders.setCreateTime(LocalDateTime.now());
        orders.setUpdateTime(LocalDateTime.now());
        orders.setPaymentDetails(paymentDetails);
        orders.setTotal(ordersInsertDto.getTotal()); //要從cartItem撈出來算
        orders.setPaymentPrice(ordersInsertDto.getTotal());
        orders.setPickupTime(ordersInsertDto.getPickupTime());
        orders.setCouponName(ordersInsertDto.getCouponName());
        orders.setUsedBunnyCoins(ordersInsertDto.getUsedBunnyCoins());
        orders.setPickupStatus(ordersInsertDto.getPickupStatus());


        ordersRepository.save(orders);
    }
}
