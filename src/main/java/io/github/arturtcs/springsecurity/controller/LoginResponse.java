package io.github.arturtcs.springsecurity.controller;

public record LoginResponse(String accessToken, Long expiresIn) { }
