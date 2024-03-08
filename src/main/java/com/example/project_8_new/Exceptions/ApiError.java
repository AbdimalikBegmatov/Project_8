package com.example.project_8_new.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {
    private Integer httpStatus;
    private LocalDateTime date;
    private Map<String, List<String>> errors;

    public ApiError(Integer httpStatus, LocalDateTime date, Map<String, List<String>> errors) {
        this.httpStatus = httpStatus;
        this.date = date;
        this.errors = errors;
    }
}
