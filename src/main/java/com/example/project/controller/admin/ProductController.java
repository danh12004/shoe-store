package com.example.project.controller.admin;

import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ProductResponse;
import com.example.project.service.ICategoryService;
import com.example.project.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProductService productService;
    ICategoryService categoryService;

    @GetMapping("/product")
    public String showProduct(Model model) {
        List<ProductResponse> products = productService.getAll();
        model.addAttribute("products", products);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        ProductResponse product = new ProductResponse();
        List<CategoryResponse> categories = categoryService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/product/edit";
    }

    @GetMapping("/edit-product")
    public String updateProduct(Model model, @RequestParam(value = "id", required = false) String id) {
        ProductResponse productDTO = productService.findById(id);
        List<CategoryResponse> categories = categoryService.getAll();
        model.addAttribute("product", productDTO);
        model.addAttribute("categories", categories);
        return "admin/product/edit";
    }
}
