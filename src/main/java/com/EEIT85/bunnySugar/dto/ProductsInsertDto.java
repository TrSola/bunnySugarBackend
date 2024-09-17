package com.EEIT85.bunnySugar.dto;


public class ProductsInsertDto extends ProductsAdminBaseDto {

    public ProductsInsertDto() {
        super();
    }

    public ProductsInsertDto(String name, Integer stocks, String description, Integer price, String imageUrl, String materialDescription, String categoryDescription, Boolean enable, String categoryName, String flavor) {
        super(name, stocks, description, price, imageUrl, materialDescription, categoryDescription, enable, categoryName, flavor);
    }
}
