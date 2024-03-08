package com.example.project_8_new.Repositories;

import com.example.project_8_new.Dto.ClientLiteDto;
import com.example.project_8_new.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("select new com.example.project_8_new.Dto.ClientLiteDto(c.id, c.email,c.password) from Client c where c.email = :email")
    Optional<ClientLiteDto> findByEmailLite(@Param("email") String email);
    Optional<Client> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<Client> findByCodeConfirm(String code);
}
