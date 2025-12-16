package io.github.arturtcs.springsecurity.service;

import io.github.arturtcs.springsecurity.controller.LoginRequest;
import io.github.arturtcs.springsecurity.controller.LoginResponse;

public interface TokenService {

    public LoginResponse getToken(LoginRequest loginRequest);
}
