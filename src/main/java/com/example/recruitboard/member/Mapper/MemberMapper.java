package com.example.recruitboard.member.Mapper;

import com.example.recruitboard.member.Dto.MemberDto;
import com.example.recruitboard.member.Entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToEntity(MemberDto.Post post);
    Member memberPatchDtoToEntity(MemberDto.Patch patch);
}
