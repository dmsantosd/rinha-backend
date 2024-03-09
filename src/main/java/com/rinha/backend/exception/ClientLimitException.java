package com.rinha.backend.exception;

public class ClientLimitException extends RuntimeException {
    public ClientLimitException(String message) {
        super(message);
    }
}