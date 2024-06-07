package com.example.project.api.web;

import com.example.project.configuration.CustomAuthenticationSuccessHandler;
import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.dto.response.UserResponse;
import com.example.project.mapper.UserMapper;
import com.example.project.service.impl.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("webUserAPI")
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApi {
    UserService userService;
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    UserMapper userMapper;

    @PostMapping("/user")
    public ResponseDTO<UserResponse> createUser(@RequestBody UserCreateRequest request, HttpServletResponse response) {
        UserResponse userResponse = userService.create(request);

        String token = customAuthenticationSuccessHandler.generateToken(userMapper.toUserEntity(request));

        Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        return ResponseDTO.<UserResponse>builder()
                .message("Create permission successfully.")
                .result(userResponse)
                .build();
    }

    @PutMapping("/user/{id}")
    public ResponseDTO<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ResponseDTO.<UserResponse>builder()
                .message("Update permission successfully.")
                .result(userService.update(id, request))
                .build();
    }
}
