package com.example.project_8_new.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientResponseDto {
    private String id;
    private String login;
    private String email;
    private String image;
    private Boolean isActivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ClientResponseDto(String id,String login, String email, String image, Boolean isActivate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.image = image;
        this.isActivate = isActivate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
