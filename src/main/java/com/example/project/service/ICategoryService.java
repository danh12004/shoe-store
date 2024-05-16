package com.example.project.service;

import com.example.project.dto.request.CategoryCreateRequest;
import com.example.project.dto.request.CategoryUpdateRequest;
import com.example.project.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse findById(String id);
    CategoryResponse findByCategoryName(String categoryName);
    CategoryResponse create(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse update(String id, CategoryUpdateRequest categoryUpdateRequest);
    void delete(String[] ids);
}
