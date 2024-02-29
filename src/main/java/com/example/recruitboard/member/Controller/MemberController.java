package com.example.recruitboard.member.Controller;

import com.example.recruitboard.member.Dto.CustomUserDetails;
import com.example.recruitboard.member.Dto.MemberDto;
import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.member.Mapper.MemberMapper;
import com.example.recruitboard.member.Service.MemberService;
import com.example.recruitboard.recruit.Entity.Recruit;
import com.example.recruitboard.recruit.Service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final RecruitService recruitService;
    private final MemberMapper mapper;

    @GetMapping("/join")
    public String join(){
        return "member/join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(MemberDto.Post post){
        memberService.joinUser(mapper.memberPostDtoToEntity(post));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "member/login";
    }

    @PostMapping("/loginProc")
    public String loginProcess(Model model){
        return "main";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        return "main";
    }

    // MemberController.java
    @GetMapping("/mypage")
    public String myPage(Model model) {
        Member member = memberService.getLoginUser();
        List<Recruit> recruits = recruitService.getRecruitsByMember(member);
        model.addAttribute("member", member);
        model.addAttribute("recruits", recruits);
        return "member/mypage";
    }

    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable("memberId") Long memberId){
        memberService.deleteUser(memberId);
        return "redirect:/login";
    }

    @GetMapping("/update")
    public String updatePage(Model model){
        Member findMember = memberService.getLoginUser();
        model.addAttribute("member", findMember);
        return "member/update";
    }
    @PostMapping("/update/{memberId}")
    public String updatePage(@PathVariable("memberId") Long memberId, Model model, MemberDto.Patch patch){
        Member findMember = memberService.updateMember(memberId,mapper.memberPatchDtoToEntity(patch));
        model.addAttribute("member", findMember);
        return "member/mypage";
    }
}
