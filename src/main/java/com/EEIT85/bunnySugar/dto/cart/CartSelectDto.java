package com.EEIT85.bunnySugar.dto.cart;

public class CartSelectDto extends CartBaseDto {

    private Integer total;
    private Integer quantity;
    private String productName;
    private String imageUrl;

    public CartSelectDto() {
    }

    public CartSelectDto(Integer total, Integer quantity, String productName, String imageUrl) {
        this.total = total;
        this.quantity = quantity;
        this.productName = productName;
        this.imageUrl = imageUrl;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
}
