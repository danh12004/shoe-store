package com.example.project.api.admin;

import com.example.project.dto.request.EmployeeCreateRequest;
import com.example.project.dto.request.EmployeeUpdateRequest;
import com.example.project.dto.response.EmployeeResponse;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.service.impl.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeAPI {
    EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
        return ResponseDTO.<EmployeeResponse>builder()
                .message("Success!")
                .result(employeeService.create(employeeCreateRequest))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDTO<EmployeeResponse> updateUser(@PathVariable String id, @RequestBody EmployeeUpdateRequest request) {
        return ResponseDTO.<EmployeeResponse>builder()
                .message("Update permission successfully.")
                .result(employeeService.update(id, request))
                .build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@RequestBody String[] ids) {
        employeeService.delete(ids);
    }
}
