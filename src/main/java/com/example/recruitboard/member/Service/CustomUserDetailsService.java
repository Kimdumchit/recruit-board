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
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByUsername(username);
        return new CustomUserDetails(findMember);
    }
}

