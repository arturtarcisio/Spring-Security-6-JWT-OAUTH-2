package io.github.arturtcs.springsecurity.controller;

import io.github.arturtcs.springsecurity.dto.LoginRequestDTO;
import io.github.arturtcs.springsecurity.dto.LoginResponseDTO;
import io.github.arturtcs.springsecurity.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO loginRequestDTO) {
        var loginResponse = tokenService.getToken(loginRequestDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
