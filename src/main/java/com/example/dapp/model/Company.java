package com.example.dapp.model;

import com.example.dapp.dto.CompanyRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Product> productList = new ArrayList<>();

    public Company(CompanyRequestDto requestDto) {
        this.name = requestDto.getName();
    }

}
