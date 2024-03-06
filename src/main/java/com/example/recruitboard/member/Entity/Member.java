package com.example.recruitboard.member.Entity;

import com.example.recruitboard.global.BaseTimeEntity;
import com.example.recruitboard.recruit.Entity.Recruit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_recruit",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "recruit_id"))
    private List<Recruit> recruits = new ArrayList<>();
}

