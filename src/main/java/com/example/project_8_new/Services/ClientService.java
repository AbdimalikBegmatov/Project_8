package com.example.project_8_new.Services;

import com.example.project_8_new.Dto.ClientRequestDto;
import com.example.project_8_new.Entity.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService extends UserDetailsService {
    Client create(ClientRequestDto clientRequestDto);
}
