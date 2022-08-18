package com.example.dapp.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private Long price;
    private Long quantity;
    private Double pointRate;
    private String companyName;
}
