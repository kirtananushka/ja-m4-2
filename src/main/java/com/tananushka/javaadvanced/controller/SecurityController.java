package com.tananushka.javaadvanced.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

    @GetMapping("/")
    public String index() {
        logUserInfo("/");
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError) {
        logUserInfo("/login");
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        logUserInfo("/logoutSuccess");
        return "logout-success";
    }

    private void logUserInfo(String page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        log.info("Page '{}' accessed by: {} with roles: {}", page, currentPrincipalName, roles);
    }
}
