package com.EEIT85.bunnySugar.dto.cart;

import org.springframework.stereotype.Component;

@Component
public class CartSelectDto extends CartBaseDto {

    private  Long id;
    private Integer price;
    private Integer quantity;
    private String productName;
    private String imageUrl;

    public CartSelectDto() {
    }

    public CartSelectDto(Long id, Integer price, Integer quantity, String productName
            , String imageUrl) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
        this.imageUrl = imageUrl;
    }

    public Integer getTotal() {
        return price;
    }

    public void setTotal(Integer total) {
        this.price = total;
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
}
