package com.EEIT85.bunnySugar.dto.products;

public class ProductsUpdateDto extends ProductsAdminBaseDto {

    private final Long id;
    private Long categoriesId;

    public ProductsUpdateDto(Long id, String productName, Integer stocks,
                             String description, Integer price, String imageUrl,
                             String materialDescription,
                             String categoryDescription, Boolean enable,
                             String categoryName, String flavor, Long categoriesId) {
        super(productName, stocks, description, price, imageUrl, materialDescription,
                categoryDescription, enable, categoryName, flavor);
        this.id = id;
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