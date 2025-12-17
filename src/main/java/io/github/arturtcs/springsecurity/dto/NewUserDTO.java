package io.github.arturtcs.springsecurity.dto;

import io.github.arturtcs.springsecurity.entities.Role;

import java.util.Set;

public record NewUserDTO(String username, String password, Set<Role> roles) {
}
