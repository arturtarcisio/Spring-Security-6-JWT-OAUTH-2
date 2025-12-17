package io.github.arturtcs.springsecurity.dto;

public record LoginResponseDTO(String accessToken, Long expiresIn) { }
