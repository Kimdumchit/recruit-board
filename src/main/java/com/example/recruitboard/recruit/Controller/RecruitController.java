package com.example.recruitboard.recruit.Controller;

import com.example.recruitboard.recruit.Dto.RecruitDto;
import com.example.recruitboard.recruit.Entity.Recruit;
import com.example.recruitboard.recruit.Mapper.RecruitMapper;
import com.example.recruitboard.recruit.Service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        Recruit recruit = recruitService.getRecruit(recruitId);
        model.addAttribute("recruit", recruit);
        return "recruit";
    }

    @PostMapping("/recruit/{recruitId}/join")
    public String joinRecruit(@PathVariable Long recruitId){
        recruitService.joinRecruit(recruitId);
        return "redirect:/";
    }
}
