package com.example.project_8_new.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomBadCredentialException {

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException exception){
        Map<String, List<String>> errors = new HashMap<>();

        errors.put("error",List.of("Неправильный пароль или логин"));

        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST.value(),
                        LocalDateTime.now(),
                        errors),
                HttpStatus.BAD_REQUEST);
    }
}
