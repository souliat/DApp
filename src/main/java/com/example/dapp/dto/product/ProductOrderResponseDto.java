package com.example.dapp.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderResponseDto {

    private String productName;
    private Long price;
    private Long quantity;

    public ProductOrderResponseDto(String productName, Long price, Long quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
