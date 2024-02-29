package com.example.recruitboard.recruit.Service;

import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.member.Repository.MemberRepository;
import com.example.recruitboard.member.Service.MemberService;
import com.example.recruitboard.recruit.Entity.Recruit;
import com.example.recruitboard.recruit.Repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    public void createRecruit(Recruit recruit) {
        Member findMember = memberService.getLoginUser();
        recruit.setWriter(findMember.getUsername());
        recruit.addMember(findMember);
        recruitRepository.save(recruit);
        findMember.setRecruit(recruit);
    }
    public Recruit updateRecruit(Long recruitId, Recruit recruit){
        Recruit findRecruit = findVerifiedRecruit(recruitId);
        findRecruit.setTitle(recruit.getTitle());
        findRecruit.setContent(recruit.getContent());
        findRecruit.setMaxCapacity(recruit.getMaxCapacity());
        return findRecruit;
    }

    public void joinRecruit(Long recruitId) {
        Member findMember = memberService.getLoginUser();
        Recruit findRecruit = findVerifiedRecruit(recruitId);

        if(findRecruit.getMember().contains(findMember)) {
            throw new IllegalArgumentException("이미 참여한 모집글입니다.");
        }
        if(findRecruit.getCapacity() >= findRecruit.getMaxCapacity()) {
            throw new IllegalArgumentException("정원이 초과되었습니다.");
        }

        findRecruit.setCapacity(findRecruit.getCapacity() + 1);
        findRecruit.addMember(findMember);
        findMember.setRecruit(findRecruit);
        recruitRepository.save(findRecruit);
        memberRepository.save(findMember);

    }
    public List<Recruit> getRecruitsByMember(Member member){
        return recruitRepository.findByMember(member);
    }
    public Map<String,Object> getRecruit(Long recruitId) {
        Recruit findRecruit = findVerifiedRecruit(recruitId);
        Member findMember = memberService.getLoginUser();
        boolean isWriter = findRecruit.getWriter().equals(findMember.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("recruit", findRecruit);
        map.put("isWriter", isWriter);
        return map;
    }

    public List<Recruit> getRecruits(){
        return recruitRepository.findAll();
    }

    public void deleteRecruit(Long recruitId){
        Recruit findRecruit = findVerifiedRecruit(recruitId);
        List<Member> members = findRecruit.getMember();
        for(Member member : members){
            member.setRecruit(null);
        }
        recruitRepository.deleteById(recruitId);
    }
    public List<Recruit> findByTitleContaining(String title) {
        return recruitRepository.findByTitleContaining(title);
    }

    public Recruit findVerifiedRecruit(Long recruitId){
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 모집글이 존재하지 않습니다."));
    }

}
