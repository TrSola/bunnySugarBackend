package com.EEIT85.bunnySugar.dto.orders.front;

import java.time.LocalDateTime;

public class OrderDetailsFrontDto {
    private String productName;
    private Integer quantity; // 數量
    private Integer price; //單價
    private Integer subtotal;


    public OrderDetailsFrontDto() {
    }

    public OrderDetailsFrontDto(String productName, Integer quantity, Integer price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetailsFrontDto(String productName, Integer quantity, Integer price, Integer subtotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}


