package com.EEIT85.bunnySugar.service.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

      // 封裝將 Products 轉換為 ProductsSelectDto 的方法
    private ProductsSelectDto convertToDto(Products product) {
        return new ProductsSelectDto(
                product.getId(),
                product.getProductName(),
                product.getStocks(),
                product.getProductDetails().getDescription(),
                product.getProductDetails().getPrice(),
                product.getProductDetails().getImageUrl(),
                product.getProductDetails().getMaterialDescription(),
                product.getCategories().getCategoryName(),
                product.getCategories().getFlavor(),
                product.getCategories().getCategoryDescription()
        );
    }

    // 取回所有產品，並將其轉換為 DTO
    public Page<ProductsSelectDto> getAll(Pageable pageable) {
        Page<Products> productsPage = productsRepository.findAll(pageable);
        return productsPage.map(this::convertToDto);
    }

    // 根據 ID 查詢產品，並轉換為 DTO
    public ProductsSelectDto getById(Long id) {
        Optional<Products> findProduct = productsRepository.findById(id);
        if (findProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        Products product = findProduct.get();
        return convertToDto(product);  // 使用封裝的方法進行轉換
    }

    // 獲取所有的 category 名稱
    public Set<String> getAllCategoryNames() {
        // Fetch all category names and return as a Set to remove duplicates
        List<String> categoryNames = categoriesRepository.findAllCategoryNames();
        if (categoryNames.isEmpty()) {
            throw new ResourceNotFoundException("No categories found.");
        }
        // 有序不重複
        return new LinkedHashSet<>(categoryNames);
    }

    // 查詢對應 categoryName 的所有風味名稱
    public Set<String> getFlavorsByCategoryName(String categoryName) {
        List<String> flavors = categoriesRepository.findFlavorsByCategoryName(categoryName);
        if (flavors.isEmpty()) {
            throw new ResourceNotFoundException("No flavors found for the given category.");
        }
        // 有序不重複
        return new LinkedHashSet<>(flavors);
    }

    // 根據category名稱查詢商品
    public Page<ProductsSelectDto> getProductsByCategoryName(String categoryName, Pageable pageable) {
        Page<Products> productsPage = productsRepository.findProductsByCategoryName(categoryName, pageable);
        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Category '" + categoryName + "' does not exist.");
        }

        return productsPage.map(this::convertToDto);
    }

    // 根據flavor名稱查詢
    public Page<ProductsSelectDto> getProductsByFlavor(String flavor, Pageable pageable) {
        Page<Products> productsPage = productsRepository.findProductsByFlavor(flavor, pageable);
        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Flavor '" + flavor + "' does not exist.");
        }

        return productsPage.map(this::convertToDto);
    }

    // 透過產品名稱模糊查詢
    public Page<ProductsSelectDto> searchProductsByNameLike(String keyword, Pageable pageable) {
        Page<Products> productsPage = productsRepository.findByProductNameContaining(keyword, pageable);
        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Keyword '" + keyword + "' does not exist.");
        }

        return productsPage.map(this::convertToDto);
    }
}
