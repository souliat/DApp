package com.example.dapp.repository;

import com.example.dapp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    void deleteAllByCartId(Long cartId);
}
