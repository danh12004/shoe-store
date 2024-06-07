package com.example.project.service.impl;

import com.example.project.dto.request.EmployeeCreateRequest;
import com.example.project.dto.request.EmployeeUpdateRequest;
import com.example.project.dto.response.EmployeeResponse;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.mapper.EmployeeMapper;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.IEmployeeService;
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
public class EmployeeService implements IEmployeeService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    EmployeeMapper employeeMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<EmployeeResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();

        for (User user : users) {
            boolean onlyUserRole = user.getRoles().size() == 1 &&
                    user.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase("USER"));

            if (!onlyUserRole) {
                EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(user);
                employeeResponse.setRoleName(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
                employeeResponses.add(employeeResponse);
            }
        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponse findById(String id) {
        return employeeMapper.toEmployeeResponse(userRepository.findById(id).orElseThrow());
    }

    @Override
    public EmployeeResponse create(EmployeeCreateRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException();
        }

        User user = employeeMapper.toUserEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> userRoles = new HashSet<>();

        Role defaultRole = roleRepository.findByName("USER");
        userRoles.add(defaultRole);

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId()).orElseThrow();
            userRoles.add(role);
        }

        user.setRoles(userRoles);

        return employeeMapper.toEmployeeResponse(userRepository.save(user));
    }

    @Override
    public EmployeeResponse update(String id, EmployeeUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        employeeMapper.update(user, request);
        return employeeMapper.toEmployeeResponse(userRepository.save(user));
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
