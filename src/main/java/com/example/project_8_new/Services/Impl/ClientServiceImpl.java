package com.example.project_8_new.Services.Impl;

import com.example.project_8_new.Entity.Client;
import com.example.project_8_new.Repositories.ClientRepository;
import com.example.project_8_new.Services.ClientService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Client client = clientRepository.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException(
                        String.format("User with email {%s} not found",username))
        );

        return new User(client.getEmail(), client.getPassword(), Collections.emptyList());
    }
}
