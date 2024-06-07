package com.example.project.controller.admin;

import com.example.project.dto.response.UserResponse;
import com.example.project.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
public class UserController {
    UserService userService;

    @GetMapping("/user")
    public String showUsers(Model model) {
        List<UserResponse> userResponses = userService.getAll();
        model.addAttribute("users", userResponses);
        return "/admin/user/index";
    }

    @GetMapping("/edit-user")
    public String updateUser(Model model, @RequestParam(value = "id", required = false) String id) {
        UserResponse userResponse = userService.findById(id);
        model.addAttribute("user", userResponse);
        return "/admin/user/edit";
    }
}
