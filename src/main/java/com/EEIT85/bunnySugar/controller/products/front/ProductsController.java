package com.EEIT85.bunnySugar.controller.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.service.products.front.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductsSelectDto> getAll(Pageable pageable) {
        return productsService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductsSelectDto getById(@PathVariable Long id) {
        return productsService.getById(id);
    }

    //抓取所有種類名稱
    @GetMapping("/categories")
    public Set<String> getAllCategoryNames() {
        return productsService.getAllCategoryNames();
    }

    // 根據種類名稱抓取對應的風味名稱
    @GetMapping("/categories/flavors")
    public Set<String> getFlavorsByCategoryName(@RequestParam String categoryName, Pageable pageable) {
        return productsService.getFlavorsByCategoryName(categoryName);
    }

    // 種類名稱抓商品
    @GetMapping("/category/{categoryName}")
    public Page<ProductsSelectDto> getByCategoryName(@PathVariable String categoryName, Pageable pageable) {
        return productsService.getProductsByCategoryName(categoryName, pageable);
    }

    // 風味名稱抓商品
    @GetMapping("/flavor/{flavor}")
    public Page<ProductsSelectDto> getByFlavorName(@PathVariable String flavor, Pageable pageable) {
        return productsService.getProductsByFlavor(flavor, pageable);
    }

    // 模糊查詢
    @GetMapping("/search")
    public ResponseEntity<Page<ProductsSelectDto>> searchProducts(@RequestParam String keyword, Pageable pageable) {
        Page<ProductsSelectDto> result = productsService.searchProductsByNameLike(keyword, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
