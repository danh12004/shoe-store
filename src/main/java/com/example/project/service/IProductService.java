package com.example.project.service;

import com.example.project.dto.request.ProductCreateRequest;
import com.example.project.dto.request.ProductUpdateRequest;
import com.example.project.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAll();
    ProductResponse findById(String id);
    ProductResponse findByProductName(String productName);
    ProductResponse create(ProductCreateRequest product);
    ProductResponse update(String id, ProductUpdateRequest product);
    void delete(String[] ids);
}
