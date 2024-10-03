package com.EEIT85.bunnySugar.controller.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.service.products.front.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/products")
@RestController
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping
    public List<ProductsSelectDto> getAll() {
        return productsService.getAll();
    }

    @GetMapping("/{id}")
    public ProductsSelectDto getById(@PathVariable Long id) {
        return productsService.getById(id);
    }

    @GetMapping("/categories")
    public Set<String> getAllCategoryNames() {
        return productsService.getAllCategoryNames();
    }

    @GetMapping("/categories/flavors")
    public Set<String> getFlavorsByCategoryName(@RequestParam String categoryName) {
        // 根據種類名稱獲取對應的風味列表
        return productsService.getFlavorsByCategoryName(categoryName);
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductsSelectDto> getByCategoryName(@PathVariable String categoryName) {
        return productsService.getProductsByCategoryName(categoryName);
    }

    @GetMapping("/flavor/{flavor}")
    public List<ProductsSelectDto> getByFlavorName(@PathVariable String flavor) {
        return productsService.getProductsByFlavor(flavor);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductsSelectDto>> searchProducts(@RequestParam String keyword) {
        List<ProductsSelectDto> result = productsService.searchProductsByNameLike(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
