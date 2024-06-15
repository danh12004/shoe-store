package com.example.project.controller.web;

import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ProductResponse;
import com.example.project.service.impl.CategoryService;
import com.example.project.service.impl.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("webProductController")
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    @RequestMapping
    public String product(@AuthenticationPrincipal UserDetails userDetails,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "9") Integer size,
                          @RequestParam(required = false) String brand,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false) String sort,
                          Model model) {
        Page<ProductResponse> productResponsePage;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<CategoryResponse> categoryResponses = categoryService.getAll();
        if (brand != null && !brand.isEmpty()) {
            productResponsePage = productService.getProductByCategoryNamePaged(pageable, brand);
        } else if (keyword != null && !keyword.isEmpty()) {
            productResponsePage = productService.searchProduct(pageable, keyword);
        } else if (sort != null && !sort.isEmpty()) {
            productResponsePage = productService.sortProductPrice(sort, pageable);
        } else {
            productResponsePage = productService.getAllPaged(pageable);
        }
        model.addAttribute("user", userDetails);
        model.addAttribute("products", productResponsePage);
        model.addAttribute("categories", categoryResponses);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", productResponsePage.getTotalPages());
        return "web/products";
    }
}
