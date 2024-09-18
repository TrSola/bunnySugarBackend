package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody Users user) {
        Map<String, Object> response = new HashMap<>();
        // Check if the account already exists
        Users existingUser = userService.findByUserAccount(user.getAccount());
        if (existingUser != null) {
            response.put("status", "error");
            response.put("message", "帳號已存在");
            return response;
        }

        // Register new user
        Long userId = userService.registerUserAndAll(user);
        response.put("status", "success");
        response.put("message", "註冊成功！您的會員ID: " + userId);
        return response;
    }

    @PostMapping("/verify")
    public Map<String, Object> verifyUser(@RequestBody Map<String, Object> verifyRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = verifyRequest.get("token").toString();
            boolean verificationSuccess = userService.verifyUser(token);
            if (verificationSuccess) {
                response.put("status", "success");
                response.put("message", "驗證成功，您的帳戶已啟用");
            } else {
                response.put("status", "error");
                response.put("message", "驗證失敗，請重新嘗試");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "驗證失敗，請重新嘗試");
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        // Fetch user by account
        Users loginedUser = userService.findByUserAccount(loginRequest.get("account").toString());

        if (loginedUser == null) {
            response.put("status", "error");
            response.put("message", "帳號不存在");
            return response;
        }

        // Check password
        if (!loginedUser.getPassword().equals(loginRequest.get("password").toString())) {
            response.put("status", "error");
            response.put("message", "密碼錯誤");
            return response;
        }

        // Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginedUser.getId());
        claims.put("account", loginedUser.getAccount());
        String token = JwtUtil.genToken(claims);

        response.put("status", "success");
        response.put("token", token);
        return response;
    }
}
