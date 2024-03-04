package com.example.project_8_new.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {
    private Integer httpStatus;
    private LocalDateTime date;
    Map<String,String> errors;

    public ApiError(Integer httpStatus, LocalDateTime date, Map<String, String> errors) {
        this.httpStatus = httpStatus;
        this.date = date;
        this.errors = errors;
    }
}
