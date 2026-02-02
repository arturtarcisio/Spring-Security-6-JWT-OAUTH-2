package io.github.arturtcs.springsecurity.service;

import io.github.arturtcs.springsecurity.dto.NewUserDTO;
import io.github.arturtcs.springsecurity.entities.User;

import java.util.List;

public interface UserService {

    public void newUser(NewUserDTO newUserDTO);

    List<User> findAll();
}
