package com.example.project.controller.admin;

import com.example.project.dto.response.CategoryResponse;
import com.example.project.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    ICategoryService categoryService;

    @GetMapping("/category")
    public String showCategory(Model model) {
        List<CategoryResponse> list = categoryService.getAll();
        model.addAttribute("category", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        CategoryResponse category = new CategoryResponse();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @GetMapping("/edit-category")
    public String editCategory(Model model, @RequestParam(value = "id", required = false) String id) {
        CategoryResponse category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }
}
