package com.example.project.mapper;

import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.UserResponse;
import com.example.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserCreateRequest request);
    User toUserEntity(UserResponse request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toListUser(List<User> users);
    void update(@MappingTarget User user, UserUpdateRequest request);
}
