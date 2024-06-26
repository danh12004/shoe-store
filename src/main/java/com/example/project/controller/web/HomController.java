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

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomController {
    ProductService productService;

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<ProductResponse> productResponses = productService.getAll().stream().limit(8).toList();
        model.addAttribute("products", productResponses);
        model.addAttribute("user", userDetails);
        return "web/home";
    }
}
