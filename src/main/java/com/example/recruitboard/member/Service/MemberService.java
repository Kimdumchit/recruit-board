package com.example.recruitboard.member.Service;

import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.member.Repository.MemberRepository;
import com.example.recruitboard.recruit.Entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;



@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinUser(Member member){
        boolean isExist = memberRepository.existsByUsername(member.getUsername());
        if(isExist){
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }
        member.setRole("ROLE_USER");
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public void deleteUser(Long memberId){
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }
    public Member getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return findVerifiedMember(userDetails.getUsername());
    }
    public Member updateMember(long memberId, Member member){
        Member findMember = findVerifiedMember(memberId);
        findMember.setUsername(member.getUsername());
        findMember.setEmail(member.getEmail());
        findMember.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        for(Recruit recruit : findMember.getRecruits()){
            recruit.setWriter(findMember.getUsername());
        }
        return memberRepository.save(findMember);

    }

    public Member findVerifiedMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));
    }
    public Member findVerifiedMember(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));
    }
}
