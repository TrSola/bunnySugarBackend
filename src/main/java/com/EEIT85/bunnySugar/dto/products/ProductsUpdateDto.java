package com.EEIT85.bunnySugar.dto.products;

public class ProductsUpdateDto extends ProductsAdminBaseDto {

    private final Long id;
    private Long categoriesId;

    public ProductsUpdateDto(String productName, Integer stocks, String description, Integer price, String imageUrl, byte[] img1, String materialDescription, String categoryDescription, Boolean enable, String categoryName, String flavor, Long categoriesId, Long id) {
        super(productName, stocks, description, price, imageUrl, img1, materialDescription, categoryDescription, enable, categoryName, flavor);
        this.categoriesId = categoriesId;
        this.id = id;
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