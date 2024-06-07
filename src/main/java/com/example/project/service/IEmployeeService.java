package com.example.project.service;

import com.example.project.dto.request.EmployeeCreateRequest;
import com.example.project.dto.request.EmployeeUpdateRequest;
import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.EmployeeResponse;
import com.example.project.dto.response.UserResponse;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAll();
    EmployeeResponse findById(String id);
    EmployeeResponse create(EmployeeCreateRequest request);
    EmployeeResponse update(String id, EmployeeUpdateRequest request);
    void delete(String[] ids);
}
