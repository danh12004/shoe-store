package com.example.project.service;

import com.example.project.dto.request.RoleRequest;
import com.example.project.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    List<RoleResponse> getAll();
    RoleResponse create(RoleRequest request);
    RoleResponse update(String id, RoleRequest request);
    void delete(String[] ids);
}
