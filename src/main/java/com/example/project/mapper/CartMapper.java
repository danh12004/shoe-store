package com.example.project.mapper;

import com.example.project.dto.request.CartRequest;
import com.example.project.dto.response.CartResponse;
import com.example.project.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    Cart toCartEntity(CartRequest request);
    @Mapping(source = "items", target = "cartItemResponseList")
    CartResponse toCartResponse(Cart cart);
    List<CartResponse> toListCartResponse(List<Cart> carts);
}
