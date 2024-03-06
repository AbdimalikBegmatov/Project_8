package com.example.project_8_new.Repositories;

import com.example.project_8_new.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ref.Cleaner;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);
    Boolean existsByEmail(String email);
    Client findByCodeConfirm(String code);
}
