package com.example.project_8_new.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientRequestDto {
    @NotBlank(message = "Не может быть пустым")
    @NotNull(message = "не можеть быть null")
    private String login;
    @Email(message = "Должна быть электронный почтой example@example.com")
    private String email;
    @Pattern.List({
            @Pattern(regexp = ".{8,15}", message = "От 8 до 15 символов"),
            @Pattern(regexp = ".*[0-9]", message = "Минимум 1 цифра"),
            @Pattern(regexp = ".*[@#$%^&+=!].*", message = "Минимум 1 спецсимвол (!,',#,$...)"),
            @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z]).*", message = "Строчные и прописные буквы")
    })
    private String password;
    @NotBlank(message = "Не может быть пустым")
    @NotNull(message = "не можеть быть null")
    private String passwordConfirm;

    public ClientRequestDto(String login, String email, String password, String passwordConfirm) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
