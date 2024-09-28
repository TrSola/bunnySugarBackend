package com.EEIT85.bunnySugar.dto.cart;

import org.springframework.stereotype.Component;

@Component
public class CartSelectDto {

    private Long id;
    private Integer price;
    private Integer quantity;
    private String productName;
    private String imageUrl;
    private Integer stocks;
    private Integer bunnyCoin;
    private String userVip;
    private Integer accumulateSpent;


    public CartSelectDto() {
    }

    public CartSelectDto(Long id, Integer price, Integer quantity, String productName, String imageUrl, Integer stocks, Integer bunnyCoin, String userVip, Integer accumulateSpent) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.stocks = stocks;
        this.bunnyCoin = bunnyCoin;
        this.userVip = userVip;
        this.accumulateSpent = accumulateSpent;
    }

    public Integer getAccumulateSpent() {
        return accumulateSpent;
    }

    public void setAccumulateSpent(Integer accumulateSpent) {
        this.accumulateSpent = accumulateSpent;
    }

    public String getUserVip() {
        return userVip;
    }

    public void setUserVip(String userVip) {
        this.userVip = userVip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Integer getBunnyCoin() {
        return bunnyCoin;
    }

    public void setBunnyCoin(Integer bunnyCoin) {
        this.bunnyCoin = bunnyCoin;
    }
}
