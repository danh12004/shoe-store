package com.example.project.repository;

import com.example.project.entity.Cart;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUser(User user);
}
