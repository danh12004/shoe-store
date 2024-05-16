package com.example.project.mapper;

import com.example.project.dto.request.CategoryCreateRequest;
import com.example.project.dto.request.CategoryUpdateRequest;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "products", ignore = true)
    Category toCategoryEntity(CategoryCreateRequest request);
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toListCategory(List<Category> categories);
    @Mapping(target = "products", ignore = true)
    void updateCategory(@MappingTarget Category category, CategoryUpdateRequest categoryUpdateRequest);
}
