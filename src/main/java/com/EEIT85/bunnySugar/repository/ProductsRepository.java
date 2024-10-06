package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.products.ProductsAdminSelectDto;
import com.EEIT85.bunnySugar.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,
        Long> {
    // 透過產品名稱進行模糊查詢
    Page<Products> findByProductNameContaining(String productName, Pageable pageable);

//    @Query("SELECT p.id FROM Product p WHERE p.productName = :productName")
//    Long findIdByProductName(@Param("productName") String productName);

    Products findByProductName(String productName);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.categoryName = :categoryName")
    Page<Products> findProductsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.flavor = :flavor")
    Page<Products> findProductsByFlavor(@Param("flavor") String flavor, Pageable pageable);

    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.products.ProductsAdminSelectDto" +
            "(\n" +
            "    p.id,\n" +
            "    c.categoryName,\n" +
            "    p.productName,\n" +
            "    pd.price,\n" +
            "    pd.enable,\n" +
            "    pd.description,\n" +
            "    pd.materialDescription,\n" +
            "    pd.img1,\n" +
            "    pd.img2,\n" +
            "    pd.img3,\n" +
            "    pd.img4,\n" +
            "    c.flavor,\n" +
            "    p.stocks,\n" +
            "    c.categoryDescription\n" +
            ")\n" +
            "FROM Products p\n" +
            "JOIN p.categories c\n" +
            "JOIN p.productDetails pd")
    List<ProductsAdminSelectDto> getAdminAllProducts();

    @Query("SELECT p FROM Products p LEFT JOIN FETCH p.categories LEFT JOIN FETCH p.productDetails WHERE p.id = :id")
    Products findProductWithDetailsById(@Param("id") Long id);

    @Query("SELECT NEW com.EEIT85.bunnySugar.dto.products.ProductsAdminSelectDto(" +
            "p.id, c.categoryName, p.productName, pd.price, pd.enable, pd.description, " +
            "pd.materialDescription, pd.img1, pd.img2, pd.img3, pd.img4, c.flavor, " +
            "p.stocks, c.categoryDescription) " +
            "FROM Products p " +
            "JOIN p.categories c " +
            "JOIN p.productDetails pd")
    Page<ProductsAdminSelectDto> getAdminAllProductsPaginated(Pageable pageable);
}
