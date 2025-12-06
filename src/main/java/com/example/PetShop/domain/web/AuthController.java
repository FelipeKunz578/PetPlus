package com.example.PetShop.domain.web;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.PetShop.domain.dto.LoginDTO;
import com.example.PetShop.domain.repository.AtendenteRepository;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AtendenteRepository atendenteRepo;

    public AuthController(AtendenteRepository atendenteRepo) {
        this.atendenteRepo = atendenteRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        var atendente = atendenteRepo.findByEmail(dto.getEmail()).orElse(null);
        
        if (atendente != null && atendente.getSenha().equals(dto.getSenha())) {
            return ResponseEntity.ok(Map.of(
                    "id", atendente.getId(),
                    "email", atendente.getEmail()));
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", "Credenciais inválidas"));
    }
}
