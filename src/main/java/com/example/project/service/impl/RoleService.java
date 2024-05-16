package com.example.project.service.impl;

import com.example.project.dto.request.RoleRequest;
import com.example.project.dto.response.RoleResponse;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.mapper.RoleMapper;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAll() {
        return roleMapper.toListRole(roleRepository.findAll());
    }

    @Override
    public RoleResponse create(RoleRequest request) {
        if (roleRepository.findByName(request.getName()) != null) {
            throw new RuntimeException();
        }

        return roleMapper.toRoleResponse(roleRepository.save(roleMapper.toRoleEntity(request)));
    }

    @Override
    public RoleResponse update(String id, RoleRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );
        roleMapper.update(role, request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            Role role = roleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Role not found with id: " + id)
            );

            List<User> users = userRepository.findByRolesContaining(role);

            for (User user : users) {
                user.getRoles().remove(role);
            }

            // Xóa vai trò
            roleRepository.deleteById(id);
        }
    }
}
