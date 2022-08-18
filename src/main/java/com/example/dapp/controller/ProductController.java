package com.example.dapp.controller;

import com.example.dapp.dto.GetProductResponseDto;
import com.example.dapp.dto.ProductRequestDto;
import com.example.dapp.model.Product;
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
    public Product registerProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.registerProduct(requestDto);
    }

    //상품 전체 조회
    @GetMapping("/product/search")
    public List<GetProductResponseDto> getProducts() {
        return productService.getProducts();
    }
}
