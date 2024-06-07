package com.example.project.service;


import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> getAll();
    UserResponse findById(String id);
    UserResponse create(UserCreateRequest request);
    UserResponse getById(String id);
    UserResponse update(String id, UserUpdateRequest request);
    void delete(String[] ids);
}
