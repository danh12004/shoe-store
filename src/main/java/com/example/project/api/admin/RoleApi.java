package com.example.project.api.admin;

import com.example.project.dto.request.RoleRequest;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.dto.response.RoleResponse;
import com.example.project.service.impl.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleApi {
    RoleService roleService;

    @GetMapping("/roles")
    public ResponseDTO<List<RoleResponse>> getAll() {
        return ResponseDTO.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping("/role")
    public ResponseDTO<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ResponseDTO.<RoleResponse>builder()
                .message("Create permission successfully.")
                .result(roleService.create(request))
                .build();
    }

    @PutMapping("/role/{id}")
    public ResponseDTO<RoleResponse> updateRole(@PathVariable String id, @RequestBody RoleRequest request) {
        return ResponseDTO.<RoleResponse>builder()
                .message("Update permission successfully.")
                .result(roleService.update(id, request))
                .build();
    }

    @DeleteMapping("/role")
    public void deleteRole(@RequestBody String[] ids) {
        roleService.delete(ids);
    }
}
