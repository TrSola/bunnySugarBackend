package com.EEIT85.bunnySugar.repository;


import com.EEIT85.bunnySugar.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriesRepository extends JpaRepository<Categories,
        Long> {

    @Query("SELECT c FROM Categories c WHERE c.categoryName = :categoryName AND" +
            " c" +
            ".flavor = :flavor")
    Categories findByCategoryNameAndFlavor(@Param("categoryName") String categoryName,
                                           @Param("flavor") String flavor);
}