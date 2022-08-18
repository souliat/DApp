package com.example.dapp.dto.order;

import com.example.dapp.dto.product.ProductOrderRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long memberId;
    private String paymentMethod;
//    private List<ProductOrderRequestDto> productOrderList;
}
