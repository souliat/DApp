package com.example.dapp.dto;

import com.example.dapp.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private String username;
    private Long totalPrice;
    private List<ProductOrderResponseDto> productOrderList;


    public OrderResponseDto(Member member) {
        this.username = member.getUsername();
        this.productOrderList = new ArrayList<>();
    }
}
