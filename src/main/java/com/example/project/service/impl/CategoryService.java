package com.example.project.service.impl;

import com.example.project.dto.request.CategoryCreateRequest;
import com.example.project.dto.request.CategoryUpdateRequest;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.entity.Category;
import com.example.project.entity.Product;
import com.example.project.mapper.CategoryMapper;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    ProductRepository productRepository;

    @Override
    public List<CategoryResponse> getAll() {
        return categoryMapper.toListCategory(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse findById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse findByCategoryName(String categoryName) {
        return categoryMapper.toCategoryResponse(categoryRepository.findByCategoryName(categoryName));
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryCreateRequest request) {
        if (categoryRepository.findByCategoryName(request.getCategoryName()) != null) {
            throw new RuntimeException();
        }

        return categoryMapper.toCategoryResponse(categoryRepository.save(categoryMapper.toCategoryEntity(request)));
    }

    @Override
    @Transactional
    public CategoryResponse update(String id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );

        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        for (String id : ids) {
            List<Product> productEntities = productRepository.findByCategoryId(id);
            for (Product product : productEntities) {
                productRepository.deleteById(product.getId());
            }
            categoryRepository.deleteById(id);
        }
    }
}
