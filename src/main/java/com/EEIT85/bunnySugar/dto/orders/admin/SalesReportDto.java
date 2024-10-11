package com.EEIT85.bunnySugar.dto.orders.admin;

public class SalesReportDto {
    private Long productId;
    private String productName;
    private Long sumPrice;
    private Long sumQuantity;
    private Long totalRevenue;

    public SalesReportDto() {
    }

    public SalesReportDto(Long productId, String productName, Long sumPrice, Long sumQuantity, Long totalRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.sumPrice = sumPrice;
        this.sumQuantity = sumQuantity;
        this.totalRevenue = totalRevenue;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Long sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Long getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Long sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
