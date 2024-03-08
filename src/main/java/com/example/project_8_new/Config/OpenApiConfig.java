package com.example.project_8_new.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Abdimalik",
                        email = "abdimalik.b@gmail.com"
                ),
                description = "Докунтацая по проекту 8 для работы с авторизацией",
                title = "Проект 8 Neobis",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Develop ENV",
                        url = "http://localhost:8080/"
                ),
                @Server(
                        description = "Production ENV",
                        url = "https://troubled-eyes-production.up.railway.app/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
