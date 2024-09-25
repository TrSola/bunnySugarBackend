package com.EEIT85.bunnySugar.service.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

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
    public List<ProductsSelectDto> getAll() {
        List<Products> productsList = productsRepository.findAll();
        return productsList.stream()
                .map(this::convertToDto)  // 使用封裝的方法進行轉換
                .collect(Collectors.toList());
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

    // 根據種類名稱查詢
    public List<ProductsSelectDto> getProductsByCategoryName(String categoryName) {
        List<Products> productsListByCategory = productsRepository.findByCategoryName(categoryName);
        return productsListByCategory.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 透過產品名稱模糊查詢
    public List<ProductsSelectDto> searchProductsByNameLike(String keyword) {
        List<Products> productsList = productsRepository.findByProductNameContaining(keyword);
        return productsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }
}
