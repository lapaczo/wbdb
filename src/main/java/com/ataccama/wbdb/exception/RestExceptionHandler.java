package com.ataccama.wbdb.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpHeaders headers = new HttpHeaders();
    private Map<String, Object> body;


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        log.error("Wrong input parameters.", ex);

        body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("reason", "Wrong input parameters.");

        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex, WebRequest request) {

        log.error("Entity not found.", ex);

        body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("reason", "Entity not found.");

        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, body, headers, status, request);
    }
}
