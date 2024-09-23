package com.EEIT85.bunnySugar.dto.cart;

import java.time.LocalDateTime;

public class CartInsertDto {
    private Integer total;       // 總金額
    private Integer quantity;    // 數量
    private String productName;  // 產品名稱
//    private String imageUrl;     // 圖片網址
    private Long usersId;
    private LocalDateTime createTime; // 創建時間
    private LocalDateTime updateTime; // 更新時間

    public CartInsertDto() {
    }

    public CartInsertDto(Integer total, Integer quantity, String productName, LocalDateTime createTime, LocalDateTime updateTime) {
        this.total = total;
        this.quantity = quantity;
        this.productName = productName;
//        this.imageUrl = imageUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

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

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }
}
