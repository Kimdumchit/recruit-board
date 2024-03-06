package com.example.recruitboard.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf((auth) -> auth.disable());
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/join", "/joinProc","/recruit").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/mypage/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        );

        httpSecurity.formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .permitAll());

        httpSecurity.logout((auth) -> auth.logoutUrl("/logout")
                .permitAll());

        return httpSecurity.build();
    }
}
