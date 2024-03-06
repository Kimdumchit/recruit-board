package com.example.recruitboard.recruit.Controller;

import com.example.recruitboard.member.Dto.CustomUserDetails;
import com.example.recruitboard.recruit.Dto.RecruitDto;
import com.example.recruitboard.recruit.Entity.Recruit;
import com.example.recruitboard.recruit.Mapper.RecruitMapper;
import com.example.recruitboard.recruit.Service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;
    private final RecruitMapper mapper;

    @GetMapping("/recruit/create")
    public String createPage(){
        return "createRecruit";
    }

    @PostMapping("/recruit")
    public String createRecruit(RecruitDto.Post post){
        recruitService.createRecruit(mapper.RecruitPostDtoToEntity(post));
        return "redirect:/";
    }

    @GetMapping("/")
    public String getRecruits(Model model){
        List<Recruit> recruits = recruitService.getRecruits();
        model.addAttribute("recruits", recruits);
        return "main";
    }

    @GetMapping("/recruit/{recruitId}")
    public String getRecruit(@PathVariable Long recruitId, Model model){
        Map<String, Object> recruitData = recruitService.getRecruit(recruitId);
        model.addAttribute("recruit", recruitData.get("recruit"));
        model.addAttribute("isWriter", recruitData.get("isWriter"));
        return "recruit";
    }

    @GetMapping("/recruit/{recruitId}/join")
    public String joinRecruit(@PathVariable Long recruitId){
        recruitService.joinRecruit(recruitId);
        return "redirect:/";
    }

    @GetMapping("/recruit/{recruitId}/delete")
    public String deleteRecruit(@PathVariable Long recruitId){
        recruitService.deleteRecruit(recruitId);
        return "redirect:/";
    }

    @GetMapping("/recruit/{recruitId}/update")
    public String updateRecruit(@PathVariable Long recruitId, RecruitDto.Patch patch){
        recruitService.updateRecruit(recruitId, mapper.RecruitPatchDtoToEntity(patch));
        return "redirect:/recruit/{recruitId}";
    }

    @GetMapping("/recruit/{recruitId}/edit")
    public String updatePage(@PathVariable Long recruitId, Model model){
        Recruit recruit = recruitService.findVerifiedRecruit(recruitId);
        model.addAttribute("recruit", recruit);
        return "updateRecruit";
    }

    @GetMapping("/recruit/search")
    public String findByTitleContaining(@RequestParam String title,Model model) {
        List<Recruit> findRecruit = recruitService.findByTitleContaining(title);
        model.addAttribute("recruits", findRecruit);
        return "main";
    }

}
