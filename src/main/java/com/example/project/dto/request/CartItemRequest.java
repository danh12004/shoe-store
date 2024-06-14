package com.example.project.dto.request;

import com.example.project.dto.response.CartResponse;
import com.example.project.dto.response.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemRequest {
    String id;
    ProductResponse productResponse;
}
