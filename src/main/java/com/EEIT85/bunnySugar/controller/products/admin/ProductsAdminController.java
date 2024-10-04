package com.EEIT85.bunnySugar.controller.products.admin;


import com.EEIT85.bunnySugar.dto.products.ProductsAdminSelectDto;
import com.EEIT85.bunnySugar.dto.products.ProductsInsertDto;
import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.dto.products.ProductsUpdateDto;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.service.products.admin.ProductsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin/products")
@RestController
public class ProductsAdminController {

    @Autowired
    ProductsAdminService productsAdminService;

    @GetMapping
    public List<ProductsAdminSelectDto> getAllAdminProducts() {
        return productsAdminService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> insertProducts(@RequestBody ProductsInsertDto productsInsertDto) {
        productsAdminService.insertProducts(productsInsertDto);
        System.out.println(productsInsertDto);
        return ResponseEntity.ok("成功新增");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            productsAdminService.deleteById(id);
            return ResponseEntity.ok("成功刪除");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("產品未找到");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestBody ProductsUpdateDto productsUpdateDto) {

        try {
            productsAdminService.updateProduct(id, productsUpdateDto);
            return ResponseEntity.ok("成功更新");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("產品未找到");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
        }
    }
}
