package com.EEIT85.bunnySugar.dto.products;

public abstract class ProductsAdminBaseDto {

    private String productName;
    private Integer stocks;
    private String description;
    private Integer price;
    private String imageUrl;
    private byte[] img1;
    private String materialDescription;
    private String categoryDescription;
    private Boolean enable;
    private String categoryName;
    private String flavor;

    public ProductsAdminBaseDto() {
    }

    public ProductsAdminBaseDto(String productName, Integer stocks, String description, Integer price, String imageUrl, byte[] img1, String materialDescription, String categoryDescription, Boolean enable, String categoryName, String flavor) {
        this.productName = productName;
        this.stocks = stocks;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.img1 = img1;
        this.materialDescription = materialDescription;
        this.categoryDescription = categoryDescription;
        this.enable = enable;
        this.categoryName = categoryName;
        this.flavor = flavor;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
}
