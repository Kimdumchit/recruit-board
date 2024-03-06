package com.example.recruitboard.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute()
    public Model isAuthenticated(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal().equals("anonymousUser"))
            return model.addAttribute("isAuthenticated", false);
        else
            return model.addAttribute("isAuthenticated", true);
    }
}
