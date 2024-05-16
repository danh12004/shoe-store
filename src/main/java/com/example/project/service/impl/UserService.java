package com.example.project.service.impl;

import com.example.project.dto.request.UserCreateRequest;
import com.example.project.dto.request.UserUpdateRequest;
import com.example.project.dto.response.UserResponse;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toListUser(userRepository.findAll());
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException();
        }

        User user = userMapper.toUserEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> userRoles = new HashSet<>();

        if (!request.getRole().isEmpty()) {
            Role role = roleRepository.findByName(request.getRole());
            userRoles.add(role);
        }

        user.setRoles(userRoles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResponse update(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException()
        );
        userMapper.update(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            User user = userRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("User not found with id: " + id)
            );
            user.getRoles().clear();
            userRepository.deleteById(id);
        }
    }
}
