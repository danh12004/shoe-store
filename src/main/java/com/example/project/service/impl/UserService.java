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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase("USER")))
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResponse findByUsername(String username) {
        return userMapper.toUserResponse(userRepository.findByUsername(username));
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException();
        }

        User user = userMapper.toUserEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> userRoles = new HashSet<>();

        Role defaultRole = roleRepository.findByName("USER");
        userRoles.add(defaultRole);

        if (!request.getRole().equals("USER")) {
            Role role = roleRepository.findByName(request.getRole());
            if (role != null) {
                userRoles.add(role);
            }
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
