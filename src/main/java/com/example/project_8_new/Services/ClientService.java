package com.example.project_8_new.Services;

import com.example.project_8_new.Dto.ClientResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService extends UserDetailsService {
    ClientResponseDto getDetailAccount();

    ClientResponseDto editImage(MultipartFile image);
}
