package com.example.project_8_new.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientLiteDto {
    private String id;
    private String email;
    private String password;

    public ClientLiteDto(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
