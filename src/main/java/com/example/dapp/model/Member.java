package com.example.dapp.model;

import com.example.dapp.dto.member.MemberRequestDto;
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
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private Long asset;

    @Column(nullable = false)
    private Long point;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

    public Member(MemberRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.asset = requestDto.getAsset();
        this.point = requestDto.getPoint();
    }
}
