package com.example.project_8_new.Services.Impl;

import com.example.project_8_new.Dto.ClientLiteDto;
import com.example.project_8_new.Dto.ClientResponseDto;
import com.example.project_8_new.Entity.Client;
import com.example.project_8_new.Exceptions.CustomErrorException;
import com.example.project_8_new.Repositories.ClientRepository;
import com.example.project_8_new.Services.ClientService;
import com.example.project_8_new.Services.CloudinaryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CloudinaryService cloudinaryService;

    public ClientServiceImpl(ClientRepository clientRepository, CloudinaryService cloudinaryService) {
        this.clientRepository = clientRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        ClientLiteDto client = clientRepository.findByEmailLite(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new User(client.getEmail(), client.getPassword(),Collections.emptyList());
    }

    @Override
    public ClientResponseDto getDetailAccount() {
        Client client = clientRepository
                .findByEmail(SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName())
                .orElseThrow(()->
                        new UsernameNotFoundException("Пользователь не найден")
                );

        return new ClientResponseDto(
                client.getId(),
                client.getFirstname(),
                client.getLastname(),
                client.getEmail(),
                client.getDateOfBirth(),
                client.getImage(),
                client.getIsActivate(),
                client.getCreatedAt(),
                client.getUpdatedAt()
                );
    }

    @Transactional
    @Override
    public ClientResponseDto editImage(MultipartFile image) {
        if (image.isEmpty() || !Objects.equals(image.getContentType(), "image/jpeg")){
            throw new MultipartException("File not found or not support format");
        }
        Client client = clientRepository
                .findByEmail(
                        SecurityContextHolder
                                .getContextHolderStrategy()
                                .getContext()
                                .getAuthentication()
                                .getName())
                .orElseThrow(()->
                        new UsernameNotFoundException("Пользователь не найден")
                );
        String imageUrl;
        try {
            imageUrl = cloudinaryService.uploadImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        client.setImage(imageUrl);
        Client result = clientRepository.save(client);


        return new ClientResponseDto(
                result.getId(),
                result.getFirstname(),
                result.getLastname(),
                result.getEmail(),
                result.getDateOfBirth(),
                result.getImage(),
                result.getIsActivate(),
                result.getCreatedAt(),
                result.getUpdatedAt()
        );
    }
}
