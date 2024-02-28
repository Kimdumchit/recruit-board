package com.example.recruitboard.member.Controller;

import com.example.recruitboard.member.Mapper.MemberMapper;
import com.example.recruitboard.member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;


}
