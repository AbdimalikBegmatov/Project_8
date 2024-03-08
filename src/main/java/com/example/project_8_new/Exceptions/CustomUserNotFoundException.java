package com.example.project_8_new.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomUserNotFoundException {
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ApiError> handleUserNotFoundException(UsernameNotFoundException e){
        Map<String,List<String>> errors = new HashMap<>();

        errors.put("error", List.of(e.getMessage()));

        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        LocalDateTime.now(),
                        errors),
                HttpStatus.BAD_REQUEST);
    }
}
