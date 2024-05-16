package com.example.project.mapper;

import com.example.project.dto.request.ProductCreateRequest;
import com.example.project.dto.request.ProductUpdateRequest;
import com.example.project.dto.response.ProductResponse;
import com.example.project.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category", ignore = true)
    Product toProductEntity(ProductCreateRequest request);
    ProductResponse toProductResponse(Product product);
    @Mapping(target = "category", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
