package com.EEIT85.bunnySugar.dto.orders.admin;

public class SalesReportDto {
    private String productName;
    private Long productQuantity;
    private Long productPrice;

    public SalesReportDto() {
    }

    public SalesReportDto(String productName, Long productQuantity, Long productPrice) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }
}
