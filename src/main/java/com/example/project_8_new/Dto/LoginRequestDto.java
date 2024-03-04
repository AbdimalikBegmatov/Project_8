package com.example.project_8_new.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    @Email(message = "Должна быть электронный почтой example@example.com")
    private String email;
    @NotBlank(message = "Не может быть пустым")
    @NotNull(message = "не можеть быть null")
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
