package com.example.project_8_new.Controllers;

import com.example.project_8_new.Dto.ClientRequestDto;
import com.example.project_8_new.Dto.LoginRequestDto;
import com.example.project_8_new.Dto.LoginResponseDto;
import com.example.project_8_new.Services.Impl.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authenticationService.authenticate(loginRequestDto));
    }
    @PostMapping("register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody ClientRequestDto clientRequestDto){
        return new ResponseEntity<>(authenticationService.register(clientRequestDto), HttpStatus.CREATED);
    }
    @GetMapping("email-confirm/{id}")
    public ResponseEntity<HttpStatus> email_confirm(@PathVariable("id") String id){
        return new ResponseEntity<>(authenticationService.confirm_email(id),HttpStatus.OK);
    }
//    @PostMapping("forgot-password/{email}")
//    public ResponseEntity<String> forgot_password(@PathVariable("email")String email){
//        return null;
//    }
}
