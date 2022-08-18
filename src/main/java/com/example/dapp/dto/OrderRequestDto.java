package com.example.dapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long memberId;
    private String paymentMethod;
    private List<ProductOrderRequestDto> productOrderList;
}
