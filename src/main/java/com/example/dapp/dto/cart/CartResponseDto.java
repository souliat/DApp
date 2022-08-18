package com.example.dapp.dto.cart;

import com.example.dapp.model.Cart;
import com.example.dapp.model.CartItem;
import com.example.dapp.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponseDto {

    private String username;
    private Long count;
    private Long totalPrice;
    private List<CartItem> cartItemList;

    public CartResponseDto(Member member) {
        this.username = member.getUsername();
        this.cartItemList = new ArrayList<>();
    }
}
