package com.example.recruitboard.recruit.Repository;

import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.recruit.Entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findByMember(Member member);
}

