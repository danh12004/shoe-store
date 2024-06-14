package com.example.project.controller.web;

import com.example.project.dto.response.ProductResponse;
import com.example.project.service.impl.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailController {
    ProductService productService;

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String id, Model model) {
        ProductResponse productResponse = productService.findById(id);
        model.addAttribute("user", userDetails);
        model.addAttribute("product", productResponse);
        return "web/productdetail";
    }
}
