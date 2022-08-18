package com.example.dapp.service;

import com.example.dapp.dto.CompanyRequestDto;
import com.example.dapp.model.Company;
import com.example.dapp.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    //협력사 등록
    public Company registerCompany(CompanyRequestDto requestDto) {

        Company company = new Company(requestDto);
        companyRepository.save(company);
        return company;
    }
}
