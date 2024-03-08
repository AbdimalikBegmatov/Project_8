package com.example.project_8_new.Services.Impl;

import com.example.project_8_new.Dto.ClientRequestDto;
import com.example.project_8_new.Dto.LoginRequestDto;
import com.example.project_8_new.Dto.LoginResponseDto;
import com.example.project_8_new.Entity.Client;
import com.example.project_8_new.Exceptions.CustomErrorException;
import com.example.project_8_new.Repositories.ClientRepository;
import com.example.project_8_new.Utils.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationService(JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager, ClientRepository clientRepository, EmailService emailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
            ));


        return new LoginResponseDto(jwtTokenUtils.generateToken(loginRequestDto.getEmail()));
    }

    @Transactional
    public LoginResponseDto register(ClientRequestDto clientRequestDto){
        String defaultPhoto = "https://res.cloudinary.com/drfnzmkqf/image/upload/v1709743707/istockphoto-1337144146-2048x2048_llzyxc.jpg";
        Boolean result = clientRepository.existsByEmail(clientRequestDto.getEmail());
        if (result){
            throw new CustomErrorException(String.format("Email of {%s} already exists",clientRequestDto.getEmail()));
        }

        if (!clientRequestDto.getPassword().equals(clientRequestDto.getPasswordConfirm())){
            throw new CustomErrorException("Password mismatch");
        }

        String uuid = UUID.randomUUID().toString();
        String url = "http:/localhost:8080/api/v1/auth/email-confirm/"+uuid;

        Client client = new Client(
                clientRequestDto.getFirstname(),
                clientRequestDto.getLastname(),
                clientRequestDto.getEmail(),
                bCryptPasswordEncoder.encode(clientRequestDto.getPassword()),
                clientRequestDto.getDateOfBirth(),
                defaultPhoto,
                LocalDateTime.now()
        );

        client.setCodeConfirm(uuid);
        client.setCodeConfirmBeginDate(LocalDateTime.now());

        client = clientRepository.save(client);


        emailService.sendEmail(client.getEmail(),
                "Подвердите регистрацию",
                String.format("<p>Перейдите по ссылке для завершение регистрации <a href=%s>Нажмите сюда</a></p>",url));

        return new LoginResponseDto(jwtTokenUtils.generateToken(client.getEmail()));
    }

    @Transactional
    public HttpStatus confirm_email(String id) {
        Client client = clientRepository.findByCodeConfirm(id).orElseThrow(()->new CustomErrorException("Неправильня ссылыка подверждени или ссылка повреждена"));
        if (!client.getCodeConfirmBeginDate().isBefore(client.getCodeConfirmBeginDate().plusMinutes(5)) && !client.getCodeConfirm().equals(id)){
            throw  new CustomErrorException("Время для потверждение email истек или неправельный ссылка, отправьте заново");
        }
        if (client.getIsActivate()){
            throw  new CustomErrorException("Вы уже активировали ссылку, нет надобности повторной активации");
        }

        client.setIsActivate(true);
        clientRepository.save(client);

        emailService.sendEmail(client.getEmail(),
                "Подвердите регистрацию",
                "Вы успешно завершили подверждение");

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus newEmailConfirmUrl() {

        Client client = clientRepository.findByEmail(SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName()).orElseThrow(()-> new CustomErrorException("Приозошла ошибка при отправление повторного подверждение email"));
        if (client.getIsActivate()){
            throw  new CustomErrorException("Вы уже активировали ссылку, нет надобности повторной активации");
        }
        String uuid = UUID.randomUUID().toString();
        String url = "http:/localhost:8080/api/v1/auth/email-confirm/"+uuid;

        client.setCodeConfirm(uuid);
        client.setCodeConfirmBeginDate(LocalDateTime.now());

        Client result = clientRepository.save(client);


        emailService.sendEmail(result.getEmail(),
                "Подвердите регистрацию",
                String.format("<p>Перейдите по ссылке для завершение регистрации <a href=%s>Нажмите сюда</a></p>",url));


        return HttpStatus.OK;
    }
}
