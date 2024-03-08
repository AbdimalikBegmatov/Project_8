package com.example.project_8_new.Exceptions;

public class CustomErrorException extends RuntimeException{
    public CustomErrorException(String message) {
        super(message);
    }

    public CustomErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
