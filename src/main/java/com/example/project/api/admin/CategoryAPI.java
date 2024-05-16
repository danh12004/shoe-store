package com.example.project.api.admin;

import com.example.project.dto.request.CategoryCreateRequest;
import com.example.project.dto.request.CategoryUpdateRequest;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryAPI {
    ICategoryService categoryService;

    @PostMapping("/category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return ResponseDTO.<CategoryResponse>builder()
                .message("Create category successfully.")
                .result(categoryService.create(categoryCreateRequest))
                .build();
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryUpdateRequest request) {
        return ResponseDTO.<CategoryResponse>builder()
                .message("Update category successfully.")
                .result(categoryService.update(id, request))
                .build();
    }

    @DeleteMapping("/category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@RequestBody String[] ids) {
        categoryService.delete(ids);
    }
}
