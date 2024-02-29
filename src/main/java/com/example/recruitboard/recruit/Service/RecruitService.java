package com.example.recruitboard.recruit.Service;

import com.example.recruitboard.member.Entity.Member;
import com.example.recruitboard.member.Repository.MemberRepository;
import com.example.recruitboard.member.Service.MemberService;
import com.example.recruitboard.recruit.Entity.Recruit;
import com.example.recruitboard.recruit.Repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    public void createRecruit(Recruit recruit) {
        Member findMember = memberService.getLoginUser();
        recruit.setMember(findMember);
        findMember.getRecruits().add(recruit);
        recruitRepository.save(recruit);
    }
    public Recruit updateRecruit(Long recruitId, Recruit recruit){
        Recruit findRecruit = findVerifiedRecruit(recruitId);
        findRecruit.setTitle(recruit.getTitle());
        findRecruit.setContent(recruit.getContent());
        findRecruit.setMaxCapacity(recruit.getMaxCapacity());
        return recruitRepository.save(findRecruit);
    }

    public void joinRecruit(Long recruitId) {
        Member findMember = memberService.getLoginUser();
        Recruit findRecruit = findVerifiedRecruit(recruitId);

        // 모집이 최대 인원에 도달한 경우
        if (findRecruit.getCapacity() >= findRecruit.getMaxCapacity()) {
            throw new IllegalArgumentException("모집 인원이 꽉 찼습니다.");
        }

        // 이미 참여한 모집에 대한 체크
        if (findMember.getRecruits().contains(findRecruit)) {
            throw new IllegalArgumentException("이미 참여한 모집글입니다.");
        }

        // 모집에 회원을 추가하고 모집의 참여 인원 수 증가
        findMember.addRecruit(findRecruit);
        findRecruit.setCapacity(findRecruit.getCapacity() + 1);

        // 변경된 내용 저장
        recruitRepository.save(findRecruit);
    }
    public List<Recruit> getRecruitsByMember(Member member){
        return recruitRepository.findByMember(member);
    }
    public Recruit getRecruit(Long recruitId) {
        return findVerifiedRecruit(recruitId);
    }

    public List<Recruit> getRecruits(){
        return recruitRepository.findAll();
    }

    public void deleteRecruit(Long recruitId){
        recruitRepository.delete(findVerifiedRecruit(recruitId));
    }

    public Recruit findVerifiedRecruit(Long recruitId){
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 모집글이 존재하지 않습니다."));
    }

}
