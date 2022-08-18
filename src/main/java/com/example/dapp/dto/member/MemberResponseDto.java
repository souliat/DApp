package com.example.dapp.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private String status;
    private String message;

    public void ok() {
        this.status = "true";
        this.message = "회원등록 완료!";
    }

}
