package com.tananushka.javaadvanced;

import com.tananushka.javaadvanced.entity.UserEntity;
import com.tananushka.javaadvanced.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printUserEntities(UserRepository repository) {
        return args -> {
            List<UserEntity> users = repository.findAll();
            log.info("UserEntity records:");
            log.info("--------------------");
            for (UserEntity user : users) {
                log.info("\n" + user.toString());
            }
            log.info("");
        };
    }
}
