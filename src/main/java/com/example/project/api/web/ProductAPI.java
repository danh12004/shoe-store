package com.example.project.api.web;

import com.example.project.dto.response.ProductResponse;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.service.impl.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("webProductAPI")
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductAPI {
    ProductService productService;

    @GetMapping("/product/search")
    public ResponseDTO<List<ProductResponse>> searchProducts(@RequestParam String query) {
        List<ProductResponse> products = productService.searchProduct(query);
        return ResponseDTO.<List<ProductResponse>>builder()
                .message("Create product successfully.")
                .result(products)
                .build();
    }
}
