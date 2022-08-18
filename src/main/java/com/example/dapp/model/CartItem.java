package com.example.dapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long count;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="CART_ID")
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="PRODUCT_ID")
    private Product product;


    public CartItem(Cart cart, Product product, Long count) {
        this.cart = cart;
        this.product = product;
        this.count = count;
    }
}
