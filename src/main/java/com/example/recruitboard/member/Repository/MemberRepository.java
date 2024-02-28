package com.example.recruitboard.member.Repository;

import com.example.recruitboard.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
