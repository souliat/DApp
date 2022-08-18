package com.example.dapp.service;

import com.example.dapp.dto.cart.AddProductCartRequestDto;
import com.example.dapp.dto.cart.CartRequestDto;
import com.example.dapp.dto.cart.CartResponseDto;
import com.example.dapp.model.Cart;
import com.example.dapp.model.CartItem;
import com.example.dapp.model.Member;
import com.example.dapp.model.Product;
import com.example.dapp.repository.CartItemRepository;
import com.example.dapp.repository.CartRepository;
import com.example.dapp.repository.MemberRepository;
import com.example.dapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    // 회원 고유 장바구니 생성(1인당 1개만 생성 가능)
    public void cartCreate(CartRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new NullPointerException("해당하는 아이디가 존재하지 않습니다."));

        Cart cart = cartRepository.findByMemberId(member.getId());

        if(cart == null) {
            cart = new Cart(member);
            cartRepository.save(cart);
        }else {
            throw new NonUniqueResultException("회원 1명 당 1개의 장바구니만 유효합니다.");
        }

    }

    // 장바구니 조회
    public CartResponseDto cartLookup(CartRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new NullPointerException("해당하는 아이디가 존재하지 않습니다."));

        Cart cart = cartRepository.findByMemberId(member.getId());

        CartResponseDto cartResponseDto = new CartResponseDto(member);

        long totalPrice = 0L;
        long count = 0;
        for (CartItem cartItem : cart.getCartItemList()) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getCount();
            count += 1;
            cartResponseDto.getCartItemList().add(cartItem);
        }
        cartResponseDto.setTotalPrice(totalPrice);
        cartResponseDto.setCount(count);

        return cartResponseDto;
    }

    // 장바구니에 상품 추가
    @Transactional
    public void cartProductAdd(AddProductCartRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(
                () -> new NullPointerException("해당하는 아이디가 존재하지 않습니다."));

        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new NullPointerException("해당하는 상품이 존재하지 않습니다."));

        Cart cart = cartRepository.findByMemberId(member.getId());

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if(cartItem == null) {
            cartItem = new CartItem(cart, product, requestDto.getCount());
            cart.getCartItemList().add(cartItem);
            cartItemRepository.save(cartItem);
            cart.setTotalCount(cart.getTotalCount() + 1);
        }else {
            cartItem.setCount(cartItem.getCount() + requestDto.getCount());
        }

    }

    // 장바구니 개별 상품 삭제
    @Transactional
    public void cartProductDelete(Long cartItemId, CartRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new NullPointerException("잘못된 접근입니다. 해당 상품은 존재하지 않습니다.")
        );
        Cart cart = cartRepository.findByMemberId(requestDto.getMemberId());

        cartItemRepository.deleteById(cartItem.getId());

        cart.setTotalCount(cart.getTotalCount() - 1);

    }

    // 장바구니 전체 삭제
    @Transactional
    public void cartDelete(CartRequestDto requestDto) {

        Cart cart = cartRepository.findByMemberId(requestDto.getMemberId());
        cartItemRepository.deleteAllByCartId(cart.getId());
        cart.setTotalCount(0L);
    }
}
