package com.example.project.api.admin;

import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.dto.response.UserResponse;
import com.example.project.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminUserAPI")
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAPI {
    UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<List<UserResponse>> getAll() {
        return ResponseDTO.<List<UserResponse>>builder()
                .message("Success!")
                .result(userService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ResponseDTO.<UserResponse>builder()
                .message("Update permission successfully.")
                .result(userService.update(id, request))
                .build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@RequestBody String[] ids) {
        userService.delete(ids);
    }
}
