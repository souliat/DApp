package com.example.dapp.controller;

import com.example.dapp.dto.product.GetProductResponseDto;
import com.example.dapp.dto.product.ProductRequestDto;
import com.example.dapp.dto.product.ProductResponseDto;
import com.example.dapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    //상품 등록
    @PostMapping("/product/register")
    public ProductResponseDto registerProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.registerProduct(requestDto);
    }

    //상품 전체 조회
    @GetMapping("/product/lookup")
    public List<GetProductResponseDto> getProducts() {
        return productService.getProducts();
    }
}
