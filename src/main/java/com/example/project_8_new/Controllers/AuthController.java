package com.example.project_8_new.Controllers;

import com.example.project_8_new.Dto.ClientRequestDto;
import com.example.project_8_new.Dto.LoginRequestDto;
import com.example.project_8_new.Dto.LoginResponseDto;
import com.example.project_8_new.Services.Impl.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }
    @PostMapping("register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody @Valid ClientRequestDto clientRequestDto){
        return new ResponseEntity<>(authenticationService.register(clientRequestDto), HttpStatus.CREATED);
    }
    @GetMapping("email-confirm/{id}")
    public ResponseEntity<HttpStatus> emailConfirm(@PathVariable("id") String id){
        return new ResponseEntity<>(authenticationService.confirm_email(id),HttpStatus.OK);
    }
    @GetMapping("new_email-confirm")
    public ResponseEntity<HttpStatus> emailConfirm(){
        return new ResponseEntity<>(authenticationService.newEmailConfirmUrl(),HttpStatus.OK);
    }
}
