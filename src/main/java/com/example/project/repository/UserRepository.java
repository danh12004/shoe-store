package com.example.project.repository;

import com.example.project.entity.Role;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String userName);
    List<User> findByRolesContaining(Role role);
}
