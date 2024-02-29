package com.example.recruitboard.recruit.Entity;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
