package com.example.project_8_new.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "dateOfBirth",nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "image")
    private String image;
    @Column(name = "codeConfirm")
    private String codeConfirm;
    @Column(name = "codeConfirmBeginDate")
    private LocalDateTime codeConfirmBeginDate;
    @Column(name = "isActivate")
    private Boolean isActivate;

    @Column(name = "createdAt",updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        isActivate = false;
    }

    public Client(String login,String email, String password, LocalDate dateOfBirth, String image, LocalDateTime updatedAt) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
        this.updatedAt = updatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
