package com.example.dapp.service;

import com.example.dapp.dto.member.MemberRequestDto;
import com.example.dapp.dto.member.MemberResponseDto;
import com.example.dapp.model.Member;
import com.example.dapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 등록
    public MemberResponseDto registerMember(MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        memberRepository.save(member);

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.ok();
        return memberResponseDto;
    }
}
