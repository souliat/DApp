package com.example.dapp.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String status;
    private String message;

    public void ok() {
        this.status = "true";
        this.message = "상품등록 완료!";
    }
}
