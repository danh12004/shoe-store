package com.example.project.dto.request;

import com.example.project.dto.response.CartItemResponse;
import com.example.project.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
    String id;
    List<CartItemResponse> cartItemResponseList;
    UserResponse userResponse;
}
