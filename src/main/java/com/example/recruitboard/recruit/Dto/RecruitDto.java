package com.example.recruitboard.recruit.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RecruitDto {
    @Getter @Setter
    public static class Post{
        private String title;
        private String content;
        private int maxCapacity;
    }
    @Getter
    @Setter
    public static class Patch{
        private String title;
        private String content;
        private int maxCapacity;
    }
}
