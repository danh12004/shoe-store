package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductName(String productName);
    List<Product> findByCategoryId(String id);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategory_CategoryName(String categoryName, Pageable pageable);
    List<Product> findByProductNameContainingIgnoreCase(String productName);
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    Page<Product> searchProduct(String keyword, Pageable pageable);
}
