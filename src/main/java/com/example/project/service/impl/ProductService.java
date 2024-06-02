package com.example.project.service.impl;

import com.example.project.dto.request.ProductCreateRequest;
import com.example.project.dto.request.ProductUpdateRequest;
import com.example.project.dto.response.ProductResponse;
import com.example.project.entity.Product;
import com.example.project.mapper.ProductMapper;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = productMapper.toProductResponse(product);
            productResponse.setCategoryName(product.getCategory().getCategoryName());
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse findByProductName(String productName) {
        return productMapper.toProductResponse(productRepository.findByProductName(productName));
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.findByProductName(request.getProductName()) != null) {
            throw new RuntimeException();
        }

        Product product = productMapper.toProductEntity(request);
        product.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new RuntimeException()
        ));
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(String id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );

        productMapper.updateProduct(product, request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public Page<ProductResponse> getAllPaged(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> {
            ProductResponse productResponse = productMapper.toProductResponse(product);
            productResponse.setCategoryName(product.getCategory().getCategoryName());
            return productResponse;
        });
    }

    @Override
    public Page<ProductResponse> getProductByCategoryNamePaged(Pageable pageable, String categoryName) {
        Page<Product> productPage = productRepository.findByCategory_CategoryName(categoryName, pageable);
        return productPage.map(product -> {
            ProductResponse productResponse = productMapper.toProductResponse(product);
            productResponse.setCategoryName(product.getCategory().getCategoryName());
            return productResponse;
        });
    }
}
