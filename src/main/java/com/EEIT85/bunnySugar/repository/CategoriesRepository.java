package com.EEIT85.bunnySugar.repository;


import com.EEIT85.bunnySugar.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,
        Long> {

    @Query("SELECT c FROM Categories c WHERE c.categoryName = :categoryName AND" +
            " c" +
            ".flavor = :flavor")
    Categories findByCategoryNameAndFlavor(@Param("categoryName") String categoryName,
                                           @Param("flavor") String flavor);

    @Query("SELECT c.categoryName FROM Categories c")
    List<String> findAllCategoryNames();

    @Query("SELECT c.flavor FROM Categories c WHERE c.categoryName = :categoryName")
    List<String> findFlavorsByCategoryName(@Param("categoryName") String categoryName);

}