package com.example.dapp.controller;

import com.example.dapp.dto.cart.AddProductCartRequestDto;
import com.example.dapp.dto.cart.CartRequestDto;
import com.example.dapp.dto.cart.CartResponseDto;
import com.example.dapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    //장바구니 생성
    @PostMapping("/cart/create")
    public void cartCreate(@RequestBody CartRequestDto requestDto) {
        cartService.cartCreate(requestDto);
    }

    //장바구니 조회
    @GetMapping("/cart/lookup")
    public CartResponseDto cartLookup(@RequestBody CartRequestDto requestDto) {
       return cartService.cartLookup(requestDto);
    }

    //장바구니에 개별 상품 추가
    @PostMapping("/cart/add")
   public void cartProductAdd(@RequestBody AddProductCartRequestDto requestDto) {
        cartService.cartProductAdd(requestDto);
    }

    //장바구니 개별 상품 삭제
    @DeleteMapping("/cart/delete/{id}")
    public void cartProductDelete(@PathVariable Long id) {
        cartService.cartProductDelete(id);
    }

    //장바구니 전체 상품 삭제
    @DeleteMapping("/cart/delete")
    public void cartDelete(@RequestBody CartRequestDto requestDto) {
        cartService.cartDelete(requestDto);
    }


}
