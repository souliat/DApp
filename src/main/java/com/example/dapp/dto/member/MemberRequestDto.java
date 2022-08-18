package com.example.dapp.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private String username;
    private Long asset;
    private Long point;
}
