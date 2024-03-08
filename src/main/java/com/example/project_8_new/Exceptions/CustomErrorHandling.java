package com.example.project_8_new.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomErrorHandling {
    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ApiError> handleError(CustomErrorException ex){
        Map<String, List<String>> errors = new HashMap<>();

        errors.put("error", List.of(ex.getMessage()));

        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST.value(),
                        LocalDateTime.now(),
                        errors),
                HttpStatus.BAD_REQUEST);
    }
}
