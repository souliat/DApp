package com.example.dapp.model;

import com.example.dapp.dto.ProductRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long inventory;

    @Column(nullable = false)
    private Double pointRate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    public Product(ProductRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.inventory = requestDto.getQuantity();
        this.pointRate = requestDto.getPointRate();
    }

    public void setCompany(Company company) {
        this.company = company;
        company.getProductList().add(this);
    }
}
