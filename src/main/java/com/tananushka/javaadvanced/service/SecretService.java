package com.tananushka.javaadvanced.service;

import com.tananushka.javaadvanced.entity.Secret;
import com.tananushka.javaadvanced.exception.SecretNotFoundException;
import com.tananushka.javaadvanced.repository.SecretRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecretService {
    private final SecretRepository secretRepository;

    @Transactional
    public String createAndStoreSecret(String message) {
        String uniqueLink = UUID.randomUUID().toString();
        Secret secret = new Secret(null, uniqueLink, message, true);
        secretRepository.save(secret);
        return uniqueLink;
    }

    @Transactional
    public Secret retrieveAndDeactivateSecretByLink(String link) {
        Secret secret = secretRepository.findByUniqueLink(link)
                .orElseThrow(() -> new SecretNotFoundException("Secret with link " + link + " not found"));

        if (!secret.isActive()) {
            throw new SecretNotFoundException("The secret with link " + link + " has already been deleted.");
        }

        secret.setActive(false);
        secretRepository.save(secret);

        return secret;
    }
}