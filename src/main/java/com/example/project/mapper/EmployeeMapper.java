package com.example.project.mapper;

import com.example.project.dto.request.EmployeeCreateRequest;
import com.example.project.dto.request.EmployeeUpdateRequest;
import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.EmployeeResponse;
import com.example.project.dto.response.UserResponse;
import com.example.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    User toUserEntity(EmployeeCreateRequest request);
    EmployeeResponse toEmployeeResponse(User user);
    List<EmployeeResponse> toListEmployee(List<User> users);
    void update(@MappingTarget User user, EmployeeUpdateRequest request);
}
