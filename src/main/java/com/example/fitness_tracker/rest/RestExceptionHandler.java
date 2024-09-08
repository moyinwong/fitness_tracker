package com.example.fitness_tracker.rest;

import com.example.fitness_tracker.exceptions.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleConflict(DataIntegrityViolationException e, WebRequest request) {
        logger.error("Data Integrity violated", e);

        HashMap<String, String> errorBody = new HashMap<>();
        errorBody.put("detail", "Operation violated database constraints");
        return handleExceptionInternal(e, errorBody, HttpHeaders.EMPTY, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNotFound(NoSuchElementException e, WebRequest request) {
        logger.error("Element not found", e);

        HashMap<String, String> errorBody = new HashMap<>();
        errorBody.put("detail", e.getMessage());
        return handleExceptionInternal(e, errorBody, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnauthorized(UnauthorizedException e, WebRequest request) {
        logger.error("Unauthorized", e);

        HashMap<String, String> errorBody = new HashMap<>();
        errorBody.put("detail", e.getMessage());
        return handleExceptionInternal(e, errorBody, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnauthorized(AccessDeniedException e, WebRequest request) {
        logger.error("Forbidden", e);

        HashMap<String, String> errorBody = new HashMap<>();
        errorBody.put("detail", e.getMessage());
        return handleExceptionInternal(e, errorBody, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }
}
