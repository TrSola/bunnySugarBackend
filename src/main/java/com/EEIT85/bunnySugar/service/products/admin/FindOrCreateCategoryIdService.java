package com.EEIT85.bunnySugar.service.products.admin;

import com.EEIT85.bunnySugar.dto.products.ProductsAdminBaseDto;
import com.EEIT85.bunnySugar.entity.Categories;
import com.EEIT85.bunnySugar.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FindOrCreateCategoryIdService {




        @Autowired
        private CategoriesRepository categoriesRepository;

        public Long findOrCreateCategoryId(ProductsAdminBaseDto productsAdminBaseDto) {
            // 依照CategoryName及flavor 找出資料庫中的種類
            Categories categories =
                    categoriesRepository.findByCategoryNameAndFlavor(productsAdminBaseDto.getCategoryName(), productsAdminBaseDto.getFlavor());

            //如果沒有就創建
            if (categories == null) {
                categories = new Categories(productsAdminBaseDto.getCategoryName(),
                        productsAdminBaseDto.getFlavor(),
                        productsAdminBaseDto.getCategoryDescription(), LocalDateTime.now(),
                        LocalDateTime.now());
            }

            Categories saveCategories = categoriesRepository.save(categories);

            // 回傳該種類的 ID
            return saveCategories.getId();
        }


}

