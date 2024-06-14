package com.example.project.service.impl;

import com.example.project.dto.response.CartResponse;
import com.example.project.dto.response.UserResponse;
import com.example.project.entity.Cart;
import com.example.project.entity.CartItem;
import com.example.project.entity.Product;
import com.example.project.mapper.CartMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.CartItemRepository;
import com.example.project.repository.CartRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ICartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService implements ICartService {
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartItemRepository cartItemRepository;
    CartMapper cartMapper;
    UserMapper userMapper;
    @Override
    public CartResponse getCart(UserResponse userResponse) {
        return cartMapper.toCartResponse(cartRepository.findByUser(userMapper.toUserEntity(userResponse)));
    }

    @Override
    public CartResponse addToCart(UserResponse userResponse, String productId, int quantity) {
        Cart cart = cartRepository.findByUser(userMapper.toUserEntity(userResponse));

        if (cart == null) {
            cart = new Cart();
            cart.setUser(userMapper.toUserEntity(userResponse));
            cart = cartRepository.save(cart);
        }

        Product product = productRepository.findById(productId).orElseThrow();
        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem = cartItemRepository.save(cartItem);
            cart.getItems().add(cartItem);
        }

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse removeFromCart(UserResponse userResponse, String productId) {
        Cart cart = cartRepository.findByUser(userMapper.toUserEntity(userResponse));
        if (cart == null) return null;

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow();

        if (cartItem.getQuantity() == 1) {
            cart.getItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        }

        return cartMapper.toCartResponse(cartRepository.save(cart));
    }
}
