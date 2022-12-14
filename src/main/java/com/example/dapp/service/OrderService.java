package com.example.dapp.service;

import com.example.dapp.dto.order.OrderRequestDto;
import com.example.dapp.dto.order.OrderResponseDto;
import com.example.dapp.dto.product.ProductOrderResponseDto;
import com.example.dapp.enumeration.PaymentMethod;
import com.example.dapp.model.*;
import com.example.dapp.repository.CartItemRepository;
import com.example.dapp.repository.CartRepository;
import com.example.dapp.repository.MemberRepository;
import com.example.dapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // 주문하기
    @Transactional
    public OrderResponseDto orderCommit(OrderRequestDto requestDto) {

        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new NullPointerException("해당 아이디는 존재하지 않습니다."));

        Cart cart = cartRepository.findByMemberId(member.getId());
        List<CartItem> cartItemList = cart.getCartItemList();

        OrderResponseDto orderResponseDto = new OrderResponseDto(member);
        Order order = new Order();
        long sum = 0L;
        long pointSum = 0L;

        for(CartItem cartItem : cartItemList) {

//            Product product = productRepository.findById(productOrderRequestDto.getId()).orElseThrow(
//                    () -> new NullPointerException("등록되지 않는 상품입니다."));
            Product product = cartItem.getProduct();

            ProductOrderResponseDto productOrderResponseDto = new ProductOrderResponseDto(
                product.getName(),cartItem.getCount() * product.getPrice(), cartItem.getCount()
            );

            orderResponseDto.getProductOrderList().add(productOrderResponseDto);

            //해당 상품의 재고에서 해당 상품의 주문 수량만큼을 제거
            product.setInventory(product.getInventory() - cartItem.getCount());

            if(product.getInventory() < 0) {
                throw new IllegalArgumentException("재고 수량을 초과하여 주문할 수 없습니다.");
            }

            order.getProductList().add(product);
            order.setMember(member);

            //총 주문액 계산
            sum += productOrderResponseDto.getPrice();
            //총 포인트 누적금 계산
            pointSum += productOrderResponseDto.getPrice() * product.getPointRate();
        }

        long assetAfterPayment;
        long pointAfterPayment;

        //자산으로 구매할 경우
        if (requestDto.getPaymentMethod().equals(PaymentMethod.ASSET.getMethod())) {
            //고객 보유 잔고에서 총 주문액만큼 차감
            assetAfterPayment = member.getAsset() - sum;
            //고객 보유 포인트에서 총 포인트 누적금 만큼 증가
            pointAfterPayment = member.getPoint() + pointSum;
        //포인트로 구매할 경우
        }else {
            //고객 보유 잔고는 그대로 유지
            assetAfterPayment = member.getAsset();
            // 고객 보유 포인트에서 포인트 사용분만큼 차감
            pointAfterPayment = member.getPoint() - sum;
        }

        if (assetAfterPayment < 0) {
            throw new IllegalArgumentException("보유 자산을 초과하여 구매할 수 없습니다.");
        }
        if (pointAfterPayment < 0) {
            throw new IllegalArgumentException("보유 포인트를 초과하여 구매할 수 없습니다.");
        }

        orderRepository.save(order);

        member.setAsset(assetAfterPayment);
        member.setPoint(pointAfterPayment);
        orderResponseDto.setTotalPrice(sum);

        cartItemRepository.deleteAllByCartId(cart.getId());
        cart.setTotalCount(0L);

        return orderResponseDto;
    }
}
