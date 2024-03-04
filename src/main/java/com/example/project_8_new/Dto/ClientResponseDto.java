package com.example.project_8_new.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientResponseDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String image;
    private String codeConfirm;
    private LocalDateTime codeConfirmBeginDate;
    private Boolean isActivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
