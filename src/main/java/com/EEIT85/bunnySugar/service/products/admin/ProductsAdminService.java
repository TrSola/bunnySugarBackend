package com.EEIT85.bunnySugar.service.products.admin;

import com.EEIT85.bunnySugar.dto.products.ProductsAdminSelectDto;
import com.EEIT85.bunnySugar.dto.products.ProductsInsertDto;
import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.dto.products.ProductsUpdateDto;
import com.EEIT85.bunnySugar.entity.Categories;
import com.EEIT85.bunnySugar.entity.ProductDetails;
import com.EEIT85.bunnySugar.entity.Products;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import com.EEIT85.bunnySugar.repository.ProductDetailsRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductsAdminService {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    FindOrCreateCategoryIdService findOrCreateCategoryIdService;



    @Transactional
    public void insertProducts(ProductsInsertDto productsInsertDto) {

        //找出要插入物件的在種類表中對應的種類 如果不存在就創建findOrCreateCategoryIdService內有寫邏輯
        Long categoriesId =
                findOrCreateCategoryIdService.findOrCreateCategoryId(productsInsertDto);
        //將dto的值取出放入新產品
        Products products = new Products();
        products.setProductName(productsInsertDto.getProductName());
        products.setCreateTime(LocalDateTime.now());
        products.setUpdateTime(LocalDateTime.now());
        products.setStocks(productsInsertDto.getStocks());

        //依照上面的id找出對應的Categories物件
        Categories categories = categoriesRepository.findById(categoriesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoriesId));
        //把Categories物件賦值給products
        products.setCategories(categories);
        //存入資料庫
        Products savedProducts = productsRepository.save(products);
        //取出自增長id
        Long id = savedProducts.getId();
        //dto -> details(同上) 封裝為createProductDetails方法
        ProductDetails productDetails = createProductDetails(savedProducts, productsInsertDto);
        savedProducts.setProductDetails(productDetails);
        //因為有cascade所以可以直接save products不用特地先save details
        productsRepository.save(products);

    }


    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!productsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productsRepository.deleteById(id);
    }


    //更新不會全部屬性都都被更新可以不寫建構式(可能更麻煩)用set就好 也可以用方法包起來
    @Transactional
    public void updateProduct(Long id, ProductsUpdateDto productsUpdateDto) throws ResourceNotFoundException {

        // 確認updateDto對應的分類 沒有就創建一個
        Long categoriesId =
                findOrCreateCategoryIdService.findOrCreateCategoryId(productsUpdateDto);
        Categories categories = categoriesRepository.findById(categoriesId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoriesId));

        // 依id找出產品
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        // 更新產品信息 更新一般直接set就好 不用寫建構式 還要另外寫dto 也可以用方法包起來 如以下
        updateProductCategories(products, productsUpdateDto, categories);

        // 依products id找出對應細節表
        ProductDetails productDetails = productDetailsRepository.findByProductsId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid " +
                        "productDetails ID" + id));

        // 更新產品詳細信息
        updateProductDetails(products, productsUpdateDto, productDetails);

        // 保存更新後的產品及產品訊息(因為有cascade 修改product會自動保存details)
        productsRepository.save(products);


    }

    private ProductDetails createProductDetails(Products product, ProductsInsertDto productsInsertDto) {
        ProductDetails productDetails = new ProductDetails();
                productDetails.setProducts(product);
                productDetails.setDescription(productsInsertDto.getDescription());
                productDetails.setPrice(productsInsertDto.getPrice());
                productDetails.setDescription(productsInsertDto.getMaterialDescription());
                productDetails.setMaterialDescription(productsInsertDto.getMaterialDescription());
                productDetails.setCreateTime(LocalDateTime.now());
                productDetails.setUpdateTime(LocalDateTime.now());
                productDetails.setEnable(productsInsertDto.getEnable());
                productDetails.setImg1(productsInsertDto.getImg1());
                productDetails.setImg2(productsInsertDto.getImg2());
                productDetails.setImg3(productsInsertDto.getImg3());
                productDetails.setImg4(productsInsertDto.getImg4());
                return productDetails;
    }


    private void updateProductCategories(Products products,
                                ProductsUpdateDto productsUpdateDto, Categories categories) {
        products.setCategories(categories);
        products.setProductName(productsUpdateDto.getProductName());
        products.setUpdateTime(LocalDateTime.now());
        products.setStocks(productsUpdateDto.getStocks());
    }

    private void updateProductDetails(Products products,
                                      ProductsUpdateDto productsUpdateDto,
                                      ProductDetails productDetails) {
        productDetails.setProducts(products);
        productDetails.setDescription(productsUpdateDto.getDescription());
        productDetails.setPrice(productsUpdateDto.getPrice());
        productDetails.setMaterialDescription(productsUpdateDto.getMaterialDescription());
        productDetails.setUpdateTime(LocalDateTime.now());
        productDetails.setEnable(productsUpdateDto.getEnable());
        productDetails.setImg1(productsUpdateDto.getImg1());
        productDetails.setImg2(productDetails.getImg2());
        productDetails.setImg3(productDetails.getImg3());
        productDetails.setImg4(productDetails.getImg4());
    }


    public List<ProductsAdminSelectDto> getAll() {
        return productsRepository.getAdminAllProducts();
    }
}
