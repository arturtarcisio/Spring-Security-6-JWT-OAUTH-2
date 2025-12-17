package io.github.arturtcs.springsecurity.service;

import io.github.arturtcs.springsecurity.dto.LoginRequestDTO;
import io.github.arturtcs.springsecurity.dto.LoginResponseDTO;

public interface TokenService {

    public LoginResponseDTO getToken(LoginRequestDTO loginRequestDTO);
}
