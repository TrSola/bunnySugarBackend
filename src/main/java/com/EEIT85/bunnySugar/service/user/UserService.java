package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.entity.Cart;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private WishListService wishListService;

    // User registration logic including creating Cart and WishList
    public Long registerUserAndAll(Users user) {
        // Set default values for user registration
        user.setActive(false);  // User starts as inactive
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // Save the user in the database
        user = userRepository.save(user);

        // Create Cart for the user
        Cart cart = cartService.createCartForUser(user);

        // Create WishList for the user and cart
        wishListService.createWishListForUserAndCart(user, cart);

        // Return the user ID after successful registration
        return user.getId();
    }

    // User verification logic
    public boolean verifyUser(String token) {
        Users user = userRepository.findByVerifyingToken(token);
        if (user == null) {
            return false;  // Invalid token
        }

        // Check if token is expired
        if (user.getTokenExpirationTime().isBefore(LocalDateTime.now())) {
            return false;  // Token expired
        }

        // Update user status to active
        user.setActive(true);
        userRepository.save(user);  // Update user status in database

        return true;  // Verification successful
    }

    // Update user's isActive status by userId
    public void updateIsActiveForUser(Long userId, boolean active) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActive(active);
            userRepository.save(user);
        }
    }

    // Find user by account
    public Users findByUserAccount(String account) {
        return userRepository.findByAccount(account); // Ensure this method is present in UserRepository
    }
}
