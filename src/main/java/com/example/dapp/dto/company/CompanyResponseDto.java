package com.example.dapp.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDto {

    private String status;
    private String message;

    public void ok() {
        this.status = "true";
        this.message = "협력사 등록 완료!";
    }
}
