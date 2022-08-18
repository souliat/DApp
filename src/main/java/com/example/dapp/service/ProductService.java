package com.example.dapp.service;

import com.example.dapp.dto.GetProductResponseDto;
import com.example.dapp.dto.ProductRequestDto;
import com.example.dapp.model.Company;
import com.example.dapp.model.Product;
import com.example.dapp.repository.CompanyRepository;
import com.example.dapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;

    //상품 등록
    public Product registerProduct(ProductRequestDto requestDto) {
        Optional<Company> company = companyRepository.findByName(requestDto.getCompanyName());

        if (!company.isPresent()) {
            throw new NullPointerException("협력사를 먼저 등록한 뒤 상품을 등록해주세요!");
        }

        Product product = new Product(requestDto);
        product.setCompany(company.get());

        productRepository.save(product);
        return product;
    }

    //상품 전체 조회
    public List<GetProductResponseDto> getProducts() {
        List<GetProductResponseDto> productResponseList = new ArrayList<>();

        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            GetProductResponseDto productResponseDto = new GetProductResponseDto(product);
            productResponseList.add(productResponseDto);
        }
        return productResponseList;
    }
}
