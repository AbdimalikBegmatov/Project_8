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
    private LocalDate dateOfBirth;
    private String image;
    private Boolean isActivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ClientResponseDto(String id, String firstname, String lastname, String email, LocalDate dateOfBirth, String image, Boolean isActivate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
        this.isActivate = isActivate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
