package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;  // 主键ID

    @Column(length = 55, nullable = false, name = "account")
    private String account;

    @Column(length = 255, nullable = false, name = "password")
    private String password;

    @Column(length = 255, nullable = false, name = "email")
    private String email;

    @Column(length = 55, nullable = false, name = "name")
    private String name;

    @Column(length = 10, name = "gender")
    private String gender;

    @Column(length = 20, name = "phone")
    private String phone;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "bunny_coin")
    private Integer bunnyCoin;

    @Column(nullable = false, name = "active")
    private Boolean active;

    @Column(length = 255, name = "verifying_token")
    private String verifyingToken;

    @Column(length = 255, name = "forget_token")
    private String forgetToken;

    @Column(length = 20, name = "login_method")
    private String loginMethod;

    @Column(length = 255, name = "google_token")
    private String googleToken;

    @Column(length = 255, name = "facebook_token")
    private String facebookToken;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(nullable = false, name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "game_times")
    private Integer gameTimes;

    @Column(name = "token_expiration_time")
    private LocalDateTime tokenExpirationTime; // 新增字段

    // OneToOne關聯Cart
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Cart cart;

    // OneToOne關聯WishList
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private WishList wishList;

    public Users() {
    }

    public Users(WishList wishList, Cart cart, LocalDateTime tokenExpirationTime, Integer gameTimes, LocalDateTime lastLoginTime, LocalDateTime updateTime, LocalDateTime createTime, String facebookToken, String googleToken, String loginMethod, String forgetToken, String verifyingToken, Boolean active, Integer bunnyCoin, LocalDateTime birthday, String phone, String gender, String name, String email, String password, String account, Long id) {
        this.wishList = wishList;
        this.cart = cart;
        this.tokenExpirationTime = tokenExpirationTime;
        this.gameTimes = gameTimes;
        this.lastLoginTime = lastLoginTime;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.facebookToken = facebookToken;
        this.googleToken = googleToken;
        this.loginMethod = loginMethod;
        this.forgetToken = forgetToken;
        this.verifyingToken = verifyingToken;
        this.active = active;
        this.bunnyCoin = bunnyCoin;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.password = password;
        this.account = account;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Integer getBunnyCoin() {
        return bunnyCoin;
    }

    public void setBunnyCoin(Integer bunnyCoin) {
        this.bunnyCoin = bunnyCoin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getVerifyingToken() {
        return verifyingToken;
    }

    public void setVerifyingToken(String verifyingToken) {
        this.verifyingToken = verifyingToken;
    }

    public String getForgetToken() {
        return forgetToken;
    }

    public void setForgetToken(String forgetToken) {
        this.forgetToken = forgetToken;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getGameTimes() {
        return gameTimes;
    }

    public void setGameTimes(Integer gameTimes) {
        this.gameTimes = gameTimes;
    }

    public LocalDateTime getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(LocalDateTime tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
