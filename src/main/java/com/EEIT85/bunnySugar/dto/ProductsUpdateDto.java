package com.EEIT85.bunnySugar.dto;

public class ProductsUpdateDto extends ProductsAdminBaseDto {

    private Long id;
    private Long categoriesId;

    public ProductsUpdateDto() {
        super();
    }

    public ProductsUpdateDto(Long id, String name, Integer stocks,
                             String description, Integer price, String imageUrl,
                             String materialDescription,
                             String categoryDescription, Boolean enable,
                             String categoryName, String flavor, Long categoriesId) {
        super(name, stocks, description, price, imageUrl, materialDescription,
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