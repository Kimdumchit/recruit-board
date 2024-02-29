package com.example.recruitboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecruitBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitBoardApplication.class, args);
    }

}
