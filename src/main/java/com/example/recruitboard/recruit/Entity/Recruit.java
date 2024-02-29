package com.example.recruitboard.recruit.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.recruitboard.global.BaseTimeEntity;
import com.example.recruitboard.member.Entity.Member;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Recruit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitId;

    private String title;

    private String content;

    private String writer;

    private int maxCapacity;

    private int capacity = 1;

    @OneToMany(mappedBy = "recruit", fetch = FetchType.LAZY)
    private List<Member> member = new ArrayList<>();

    public void addMember(Member member) {
        this.member.add(member);
        member.setRecruit(this);
    }
}
