package com.rinha.backend.exception;

import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFound(ClientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class, ClientLimitException.class, DataIntegrityViolationException.class})
    public ResponseEntity<String> handleClientLimitExceeded(Exception ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getLocalizedMessage());
    }
}