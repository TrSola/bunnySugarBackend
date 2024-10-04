package com.EEIT85.bunnySugar.dto.products;

public class ProductsUpdateDto extends ProductsAdminBaseDto {

    private  Long id;
    private Long categoriesId;

    public ProductsUpdateDto(String productName, Integer stocks, String description, Integer price, String materialDescription, String categoryDescription, Boolean enable, String categoryName, String flavor, String img1, String img2, String img3, String img4, Long categoriesId) {
        super(productName, stocks, description, price, materialDescription, categoryDescription, enable, categoryName, flavor, img1, img2, img3, img4);
        this.categoriesId = categoriesId;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }
}