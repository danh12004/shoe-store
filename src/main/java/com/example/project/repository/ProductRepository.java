package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductName(String productName);
    List<Product> findByCategoryId(String id);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategory_CategoryName(String categoryName, Pageable pageable);
}
