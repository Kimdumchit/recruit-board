package com.example.recruitboard.member.Service;

import com.example.recruitboard.member.Dto.CustomUserDetails;
import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberService.findVerifiedMember(username);
        return new CustomUserDetails(findMember);
    }
}

