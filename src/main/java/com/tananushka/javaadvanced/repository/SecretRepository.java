package com.tananushka.javaadvanced.repository;

import com.tananushka.javaadvanced.entity.Secret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {
    Optional<Secret> findByUniqueLink(String uniqueLink);
}