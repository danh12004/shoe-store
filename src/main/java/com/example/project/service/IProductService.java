package com.example.project.service;

import com.example.project.dto.request.ProductCreateRequest;
import com.example.project.dto.request.ProductUpdateRequest;
import com.example.project.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAll();
    ProductResponse findById(String id);
    ProductResponse findByProductName(String productName);
    ProductResponse create(ProductCreateRequest product);
    ProductResponse update(String id, ProductUpdateRequest product);
    void delete(String[] ids);
    Page<ProductResponse> getAllPaged(Pageable pageable);
    Page<ProductResponse> getProductByCategoryNamePaged(Pageable pageable, String categoryName);
}
