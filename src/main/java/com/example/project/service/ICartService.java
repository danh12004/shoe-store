package com.example.project.service;

import com.example.project.dto.response.CartResponse;
import com.example.project.dto.response.UserResponse;

public interface ICartService {
    CartResponse getCart(UserResponse userResponse);
    CartResponse addToCart(UserResponse userResponse, String productId, int quantity);
    CartResponse removeFromCart(UserResponse userResponse, String productId);
}
