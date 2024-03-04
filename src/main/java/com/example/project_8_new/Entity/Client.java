package com.example.project_8_new.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "firstname",nullable = false)
    private String firstname;
    @Column(name = "lastname",nullable = false)
    private String lastname;
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
    @Column(name = "isActivate",columnDefinition = "false")
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

    public Client(String firstname, String lastname, String email, String password, LocalDate dateOfBirth, String image, LocalDateTime updatedAt) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
        this.updatedAt = updatedAt;
    }
}
