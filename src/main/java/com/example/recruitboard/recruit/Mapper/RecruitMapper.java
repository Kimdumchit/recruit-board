package com.example.recruitboard.recruit.Mapper;

import com.example.recruitboard.recruit.Dto.RecruitDto;
import com.example.recruitboard.recruit.Entity.Recruit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecruitMapper {
    Recruit RecruitPostDtoToEntity(RecruitDto.Post post);
    Recruit RecruitPatchDtoToEntity(RecruitDto.Patch patch);
}
