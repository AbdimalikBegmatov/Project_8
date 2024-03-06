package com.example.project_8_new.Exceptions;

public class CustomEmailAlreadyExistsException extends RuntimeException{
    public CustomEmailAlreadyExistsException(String message) {
        super(message);
    }

    public CustomEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
