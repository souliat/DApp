package com.example.dapp.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductCartRequestDto {
    private Long memberId;
    private Long productId;
    private Long count;
}
