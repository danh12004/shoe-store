package com.example.project.api.web;

import com.example.project.dto.request.AddToCartRequest;
import com.example.project.dto.request.RemoveFromCartRequest;
import com.example.project.dto.response.CartResponse;
import com.example.project.dto.response.ResponseDTO;
import com.example.project.dto.response.UserResponse;
import com.example.project.service.impl.CartService;
import com.example.project.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController("webCartAPI")
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartAPI {
    CartService cartService;
    UserService userService;

    @GetMapping
    public ResponseDTO<CartResponse> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        CartResponse cartResponse = cartService.getCart(userService.findByUsername(userDetails.getUsername()));
        return ResponseDTO.<CartResponse>builder()
                .result(cartResponse)
                .build();
    }

    @PostMapping("/add")
    public ResponseDTO<CartResponse> addToCart(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody AddToCartRequest request) {
        UserResponse userResponse = userService.findByUsername(userDetails.getUsername());
        return ResponseDTO.<CartResponse>builder()
                .message("Success!")
                .result(cartService.addToCart(userResponse, request.getProductId(), request.getQuantity()))
                .build();
    }

    @PostMapping("/remove")
    public ResponseDTO<CartResponse> removeFromCart(@AuthenticationPrincipal UserDetails userDetails,
                                                    @RequestBody RemoveFromCartRequest request) {
        UserResponse userResponse = userService.findByUsername(userDetails.getUsername());
        CartResponse cartResponse = cartService.removeFromCart(userResponse, request.getProductId());
        return ResponseDTO.<CartResponse>builder()
                .message("Success!")
                .result(cartResponse)
                .build();
    }
}
