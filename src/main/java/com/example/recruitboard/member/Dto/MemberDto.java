package com.example.recruitboard.member.Dto;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    @Getter
    @Setter
    public static class Post {

        private String email;

        private String nickName;

        private String password;

        private String role;

    }
    @Getter @Setter
    public static class Patch {
        private String email;

        private String nickName;

        private String password;
    }

}
