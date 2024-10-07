package com.EEIT85.bunnySugar.service.orders.front;

import com.EEIT85.bunnySugar.dto.orders.front.OrderDetailsFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersFrontDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInfoDto;
import com.EEIT85.bunnySugar.dto.orders.front.OrdersInsertDto;
import com.EEIT85.bunnySugar.entity.*;
import com.EEIT85.bunnySugar.repository.CartRepository;
import com.EEIT85.bunnySugar.repository.OrderDetailsRepository;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Transactional
    public void insertOrder(OrdersInsertDto ordersInsertDto, Long userId) {
        Orders orders = new Orders();
        //產生訂單編號
        orders.setOrderNumber(ordersInsertDto.getMerchantNo());

        //找出目前的使用者並將使用者資訊存入訂單
        Users user = userRepository.getReferenceById(userId);

        //計算本次消費獲得的coin
        Integer addCoin = ordersInsertDto.getTotal() / 500;
        user.setBunnyCoin(user.getBunnyCoin() + addCoin);

        //計算本次累積消費
        user.setAccumulateSpent(user.getAccumulateSpent() + ordersInsertDto.getTotal());
        Integer totalSpent = user.getAccumulateSpent();
        if(totalSpent >= 9000) {
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

        // payment部份
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setMerchantNo(ordersInsertDto.getMerchantNo());
        paymentDetails.setCreateTime(LocalDateTime.now());
        paymentDetails.setUpdateTime(LocalDateTime.now());
        paymentDetails.setPaidPrice(ordersInsertDto.getTotal());
        paymentDetails.setPaymentMethod(ordersInsertDto.getPaymentMethod());
        paymentDetails.setPaymentDate(LocalDateTime.now());
        paymentDetails.setPaymentStatus("未付款");

        //建立paymentDetails與orders的關聯
        paymentDetails.setOrders(orders);
        orders.setPaymentDetails(paymentDetails);
        orders.setCreateTime(LocalDateTime.now());
        orders.setUpdateTime(LocalDateTime.now());
        orders.setTotal(cart.getTotal());
        orders.setPaymentPrice(ordersInsertDto.getTotal());
        orders.setPickupTime(ordersInsertDto.getPickupTime());
        orders.setCouponName(ordersInsertDto.getCouponName());
        orders.setUsedBunnyCoins(ordersInsertDto.getUsedBunnyCoins());
        orders.setPickupStatus("未取貨");

        ordersRepository.save(orders);
    }

    // 查詢指定userId的訂單，並返回分頁的訂單資訊
    public Page<OrdersInfoDto> getAllOrdersByUserId(Long userId, Pageable pageable) {
        return ordersRepository.findAllOrdersByUserId(userId, pageable);
    }


    private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);
    public OrdersFrontDto getOrderByOrderNumber(String orderNumber) {
        logger.info("開始查詢訂單號: {}", orderNumber);

        OrdersFrontDto ordersFrontDto = ordersRepository.findFrontOrderByOrderNumber(orderNumber);
        if (ordersFrontDto == null) {
            logger.error("未找到訂單號為 {} 的訂單", orderNumber);
            throw new IllegalArgumentException("未找到該訂單");
        }

        logger.info("成功查詢到訂單: {}", ordersFrontDto);

        List<OrderDetailsFrontDto> orderDetailsFrontDtoList = ordersRepository.findOrderDetailsByOrderNumber(orderNumber);
        logger.info("訂單詳細資料: {}", orderDetailsFrontDtoList);

        if (orderDetailsFrontDtoList.isEmpty()) {
            logger.warn("訂單 {} 沒有詳細資料", orderNumber);
        }

        ordersFrontDto.setOrderDetails(orderDetailsFrontDtoList);


        logger.info("完成訂單查詢，返回結果: {}", ordersFrontDto);

        return ordersFrontDto;
    }
}
