package com.tananushka.javaadvanced.exception;

public class SecretNotFoundException extends RuntimeException {
    public SecretNotFoundException(String message) {
        super(message);
    }
}