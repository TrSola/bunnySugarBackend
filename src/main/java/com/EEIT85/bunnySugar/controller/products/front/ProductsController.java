package com.EEIT85.bunnySugar.controller.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.service.products.front.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
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

    @GetMapping("/search")
    public ResponseEntity<List<ProductsSelectDto>> searchProducts(@RequestParam String keyword) {
        List<ProductsSelectDto> result = productsService.searchProductsByNameLike(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
