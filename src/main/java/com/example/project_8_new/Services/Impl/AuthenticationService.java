package com.example.project_8_new.Services.Impl;

import com.example.project_8_new.Dto.ClientRequestDto;
import com.example.project_8_new.Dto.LoginRequestDto;
import com.example.project_8_new.Dto.LoginResponseDto;
import com.example.project_8_new.Entity.Client;
import com.example.project_8_new.Exceptions.CustomEmailAlreadyExistsException;
import com.example.project_8_new.Repositories.ClientRepository;
import com.example.project_8_new.Utils.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AuthenticationService {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final EmailService emailService;

    public AuthenticationService(JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager, ClientRepository clientRepository, EmailService emailService) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword())
        );

        Client client =  clientRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();

        return new LoginResponseDto(jwtTokenUtils.generateToken(client));
    }

    @Transactional
    public LoginResponseDto register(ClientRequestDto clientRequestDto){
        String defaultPhoto = "https://res.cloudinary.com/drfnzmkqf/image/upload/v1709743707/istockphoto-1337144146-2048x2048_llzyxc.jpg";
        Boolean result = clientRepository.existsByEmail(clientRequestDto.getEmail());
        if (result){
            throw new CustomEmailAlreadyExistsException(String.format("Email of {%s} already exists",clientRequestDto.getEmail()));
        }

        if (!clientRequestDto.getPassword().equals(clientRequestDto.getPasswordConfirm())){
            throw new CustomEmailAlreadyExistsException("Password mismatch");
        }

        String uuid = UUID.randomUUID().toString();
        String url = "http:/localhost:8080/api/v1/auth/email-confirm/"+uuid;

        Client client = new Client(
                clientRequestDto.getFirstname(),
                clientRequestDto.getLastname(),
                clientRequestDto.getEmail(),
                clientRequestDto.getPassword(),
                clientRequestDto.getDateOfBirth(),
                defaultPhoto,
                LocalDateTime.now()
        );

        client.setCodeConfirm(uuid);
        client.setCodeConfirmBeginDate(LocalDateTime.now());

        client = clientRepository.save(client);


        emailService.sendEmail(client.getEmail(),
                "Подвердите регистрацию",
                String.format("Перейдите по ссылке для завершение регистрации \"%s\"",url));

        return new LoginResponseDto(jwtTokenUtils.generateToken(client));
    }

    @Transactional
    public HttpStatus confirm_email(String id) {
        Client client = clientRepository.findByCodeConfirm(id).orElseThrow(()->new CustomEmailAlreadyExistsException("Неправильня ссылыка подверждени или ссылка повреждена"));
        if (!client.getCodeConfirmBeginDate().isBefore(client.getCodeConfirmBeginDate().plusMinutes(5)) && !client.getCodeConfirm().equals(id)){
            throw  new CustomEmailAlreadyExistsException("Время для потверждение email истек или неправельный ссылка, отправьте заново");
        }
        if (client.getIsActivate()){
            throw  new CustomEmailAlreadyExistsException("Вы уже активировали ссылку, нет надобности повторной активации");
        }

        client.setIsActivate(true);
        clientRepository.save(client);

        return HttpStatus.OK;
    }
}
