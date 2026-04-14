package com.example.referenceapp.auth;

import com.example.referenceapp.config.SecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String root() {
        return "redirect:" + SecurityConfig.AUTHENTICATED_LANDING_PAGE;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}

