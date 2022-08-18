package com.example.dapp.controller;

import com.example.dapp.dto.company.CompanyRequestDto;
import com.example.dapp.dto.company.CompanyResponseDto;
import com.example.dapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    // 협력사 등록
    @PostMapping("/company/register")
    public CompanyResponseDto registerCompany(@RequestBody CompanyRequestDto requestDto) {
        return companyService.registerCompany(requestDto);
    }

}
