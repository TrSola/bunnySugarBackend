package com.EEIT85.bunnySugar.dto.wishList;

import java.util.List;

public class WishListInsertDto {

    private Long userId;
    private List<Long> productIds;

    public WishListInsertDto(Long userId, List<Long> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
