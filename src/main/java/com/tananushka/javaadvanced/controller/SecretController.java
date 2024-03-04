package com.tananushka.javaadvanced.controller;

import com.tananushka.javaadvanced.dto.SecretSubmissionDto;
import com.tananushka.javaadvanced.entity.Secret;
import com.tananushka.javaadvanced.exception.SecretNotFoundException;
import com.tananushka.javaadvanced.service.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SecretController {

    private final SecretService secretService;

    @GetMapping("/submit-secret")
    @PreAuthorize("hasAuthority('STANDARD')")
    public String showSecretForm() {
        return "submit-secret";
    }

    @PostMapping("/submitSecret")
    @PreAuthorize("hasAuthority('STANDARD')")
    public String submitSecret(@ModelAttribute SecretSubmissionDto secretDto, Model model) {
        String uniqueLink = secretService.createAndStoreSecret(secretDto.getMessage());
        model.addAttribute("uniqueLink", uniqueLink);
        return "secret-submitted";
    }

    @GetMapping("/view-secret")
    @PreAuthorize("hasAuthority('STANDARD')")
    public String viewSecret(@RequestParam("link") String uniqueLink, Model model) {
        try {
            Secret secret = secretService.retrieveAndDeactivateSecretByLink(uniqueLink);
            model.addAttribute("secretMessage", secret.getMessage());
        } catch (SecretNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "view-secret";
    }
}