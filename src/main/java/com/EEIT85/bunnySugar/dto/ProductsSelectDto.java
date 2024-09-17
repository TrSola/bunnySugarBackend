package com.EEIT85.bunnySugar.dto;

public class ProductsSelectDto {
    private Long id;
    private String name;
    private Integer stocks;
    private String description;
    private Integer price;
    private String imageUrl;
    private String materialDescription;
    private String categoryName;
    private String flavor;
    private String categoryDescription;

    public ProductsSelectDto() {
    }

    public ProductsSelectDto(Long id, String name, Integer stocks, String description, Integer price, String imageUrl, String materialDescription, String categoryName, String flavor, String categoryDescription) {
        this.id = id;
        this.name = name;
        this.stocks = stocks;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.materialDescription = materialDescription;
        this.categoryName = categoryName;
        this.flavor = flavor;
        this.categoryDescription = categoryDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }


}