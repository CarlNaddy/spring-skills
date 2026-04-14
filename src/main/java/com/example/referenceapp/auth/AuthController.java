package com.example.referenceapp.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String root() {
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}

