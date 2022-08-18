package com.example.dapp.service;

import com.example.dapp.dto.company.CompanyRequestDto;
import com.example.dapp.dto.company.CompanyResponseDto;
import com.example.dapp.model.Company;
import com.example.dapp.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    //협력사 등록
    public CompanyResponseDto registerCompany(CompanyRequestDto requestDto) {

        Company company = new Company(requestDto);
        companyRepository.save(company);

        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        companyResponseDto.ok();
        return companyResponseDto;
    }
}
