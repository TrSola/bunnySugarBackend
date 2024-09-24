package com.EEIT85.bunnySugar.dto.cart;

import java.time.LocalDateTime;


public class CartInsertDto {
    private Long productId;
    private Long usersId;
    private Integer quantity;    // 數量
    private Integer price;
    private LocalDateTime createTime; // 創建時間
    private LocalDateTime updateTime; // 更新時間

    public CartInsertDto() {
    }

    public CartInsertDto(Long productId, Long usersId, Integer quantity,
                         Integer price) {
        this.quantity = quantity;
        this.productId = productId;
        this.usersId = usersId;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
