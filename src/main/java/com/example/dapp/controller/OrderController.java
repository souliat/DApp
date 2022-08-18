package com.example.dapp.controller;

import com.example.dapp.dto.OrderRequestDto;
import com.example.dapp.dto.OrderResponseDto;
import com.example.dapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    //주문하기
    @PostMapping("/order/request")
    public OrderResponseDto orderCommit(@RequestBody OrderRequestDto requestDto) {
        return orderService.orderCommit(requestDto);
    }
}
