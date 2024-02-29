package com.example.recruitboard.member.Entity;

import com.example.recruitboard.global.BaseTimeEntity;
import com.example.recruitboard.recruit.Entity.Recruit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "member")
    private List<Recruit> recruits;

    public void addRecruit(Recruit recruit){
        this.recruits.add(recruit);
    }
}
