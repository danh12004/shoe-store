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
                          Model model) {
        Page<ProductResponse> productResponsePage;
        List<CategoryResponse> categoryResponses = categoryService.getAll();
        if (brand != null && !brand.isEmpty()) {
            productResponsePage = productService.getProductByCategoryNamePaged(PageRequest.of(page - 1, size), brand);
        } else {
            productResponsePage = productService.getAllPaged(PageRequest.of(page - 1, size));
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
