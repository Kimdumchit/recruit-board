package com.example.recruitboard.member.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {

        private String email;

        private String username;

        private String password;

        private String role;

    }
    @Getter @Setter
    public static class Patch {

        private String username;

        private String email;

        private String password;
    }

}
