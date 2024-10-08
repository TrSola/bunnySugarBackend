package com.EEIT85.bunnySugar.controller.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.service.products.front.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<ProductsSelectDto> getAll(Pageable pageable, @RequestParam(required = false) String sort) {
        Pageable sortedPageable = pageable;
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);
            sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));
        }

        return productsService.getAll(sortedPageable);
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
    public Set<String> getFlavorsByCategoryName(@RequestParam String categoryName, Pageable pageable) {
        return productsService.getFlavorsByCategoryName(categoryName);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Page<ProductsSelectDto>> getByCategoryName(@PathVariable String categoryName, Pageable pageable, @RequestParam(required = false) String sort) {
        Pageable sortedPageable = pageable;
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);
            sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));
        }
        Page<ProductsSelectDto> result = productsService.getProductsByCategoryName(categoryName, sortedPageable);

        // 如果没有找到商品，仍然返回200，并返回空结果
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(sortedPageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/flavor/{flavor}")
    public ResponseEntity<Page<ProductsSelectDto>> getByFlavorName(@PathVariable String flavor, Pageable pageable, @RequestParam(required = false) String sort) {
        Pageable sortedPageable = pageable;
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);
            sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));
        }
        Page<ProductsSelectDto> result = productsService.getProductsByFlavor(flavor, sortedPageable);

        // 如果没有找到商品，返回200并返回空结果
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(sortedPageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductsSelectDto>> searchProducts(@RequestParam String keyword, Pageable pageable, @RequestParam(required = false) String sort) {
        Pageable sortedPageable = pageable;
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);
            sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortField));
        }
        Page<ProductsSelectDto> result = productsService.searchProductsByNameLike(keyword, sortedPageable);
        // 如果没有找到商品，返回200并返回空结果
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(sortedPageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

