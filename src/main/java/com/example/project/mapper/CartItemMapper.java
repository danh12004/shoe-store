package com.example.project.mapper;

import com.example.project.dto.request.CartItemRequest;
import com.example.project.dto.response.CartItemResponse;
import com.example.project.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {
    CartItem toCartItemEntity(CartItemRequest request);
    @Mapping(source = "product", target = "productResponse")
    CartItemResponse toCartItemResponse(CartItem cartItem);
    List<CartItemResponse> toCartItemResponseList(List<CartItem> cartItems);
}
