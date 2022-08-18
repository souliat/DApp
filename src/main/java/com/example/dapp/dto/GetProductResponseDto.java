package com.example.dapp.dto;

import com.example.dapp.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductResponseDto {

    private Long id;
    private String name;
    private Long price;
    private Long inventory;
    private Double pointRate;
    private String company;

    public GetProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.inventory = product.getInventory();
        this.pointRate = product.getPointRate();
        this.company = product.getCompany().getName();
    }
}
