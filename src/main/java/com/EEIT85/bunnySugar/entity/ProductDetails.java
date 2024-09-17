package com.EEIT85.bunnySugar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 主鍵ID


    @OneToOne
    //被管理的一方(避免無窮層遞)
    @JsonBackReference

    @JoinColumn(name = "products_id", nullable = false)
    private Products products;  // 對應 products 表的 ID
    @Column(length = 255, nullable = false)
    private String description;  // 商品描述
    @Column(nullable = false)
    private Integer price;  // 價格
    @Column(length = 1000, nullable = false)
    private String imageUrl;  // 圖片 URL
    @Column(length = 55, nullable = false)
    private String materialDescription;  // 組成成份
    @Column(nullable = false)
    private LocalDateTime createTime;  // 創建時間
    @Column(nullable = false)
    private LocalDateTime updateTime;  // 修改時間
    @Column(name = "enable", nullable = false)
    private Boolean enable;  // 是否啟用

    public ProductDetails() {
    }

    public ProductDetails(Products products, String description, Integer price, String imageUrl, String materialDescription, LocalDateTime createTime, LocalDateTime updateTime, Boolean enable) {
        this.products = products;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.materialDescription = materialDescription;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


}
