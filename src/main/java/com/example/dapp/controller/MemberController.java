package com.example.dapp.controller;

import com.example.dapp.dto.MemberRequestDto;
import com.example.dapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    //회원 등록
    @PostMapping("/member/register")
    public void registerMember(@RequestBody MemberRequestDto requestDto) {
        memberService.registerMember(requestDto);
    }
}
