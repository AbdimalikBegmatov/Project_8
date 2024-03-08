package com.example.project_8_new.Controllers;

import com.example.project_8_new.Dto.ClientResponseDto;
import com.example.project_8_new.Services.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/client/")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("detail")
    public ResponseEntity<ClientResponseDto> getDetailAccount(){
        return new ResponseEntity<>(clientService.getDetailAccount(), HttpStatus.OK);
    }
    @PostMapping("image")
    public ResponseEntity<ClientResponseDto> addImage(MultipartFile image){
        return new ResponseEntity<>(clientService.editImage(image), HttpStatus.OK);
    }
}
