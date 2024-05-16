package com.example.project.mapper;

import com.example.project.dto.request.RoleRequest;
import com.example.project.dto.response.RoleResponse;
import com.example.project.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRoleEntity(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
    List<RoleResponse> toListRole(List<Role> roles);
    void update(@MappingTarget Role role, RoleRequest request);
}
