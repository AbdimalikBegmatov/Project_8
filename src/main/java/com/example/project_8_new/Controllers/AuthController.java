package com.example.project_8_new.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("login")
    public ResponseEntity<String> login(){
        return null;
    }
    @PostMapping("register")
    public ResponseEntity<String> register(){
        return null;
    }
    @PostMapping("email-confirm/{id}")
    public ResponseEntity<String> email_confirm(@PathVariable("id") String id){
        return null;
    }
    @PostMapping("forgot-password/{email}")
    public ResponseEntity<String> forgot_password(@PathVariable("email")String email){
        return null;
    }
}
