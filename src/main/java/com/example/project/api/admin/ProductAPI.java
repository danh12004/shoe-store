package com.example.project.api.admin;

import com.example.project.dto.request.ProductCreateRequest;
import com.example.project.dto.request.ProductUpdateRequest;
import com.example.project.dto.response.ProductResponse;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.service.StorageService;
import com.example.project.service.impl.CategoryService;
import com.example.project.service.impl.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductAPI {
    ProductService productService;
    StorageService storageService;

    @PostMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<ProductResponse> createProduct(@RequestPart("fileImage") MultipartFile file,
                                                      @ModelAttribute ProductCreateRequest request) {
        storageService.store(file);
        request.setImage(file.getOriginalFilename());
        return ResponseDTO.<ProductResponse>builder()
                .message("Create product successfully.")
                .result(productService.create(request))
                .build();
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<ProductResponse> updateProduct(@PathVariable("id") String id,
                                                      @RequestPart("fileImage") MultipartFile file,
                                                      @ModelAttribute ProductUpdateRequest request) {
        storageService.store(file);
        request.setImage(file.getOriginalFilename());
        return ResponseDTO.<ProductResponse>builder()
                .message("Update product successfully.")
                .result(productService.update(id, request))
                .build();
    }

    @DeleteMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@RequestBody String[] ids) {
        productService.delete(ids);
    }
}
