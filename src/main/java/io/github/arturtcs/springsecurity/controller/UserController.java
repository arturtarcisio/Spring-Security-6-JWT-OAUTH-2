package io.github.arturtcs.springsecurity.controller;

import io.github.arturtcs.springsecurity.dto.NewUserDTO;
import io.github.arturtcs.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody NewUserDTO newUserDTO) {
        userService.newUser(newUserDTO);
        return ResponseEntity.ok().build();
    }
}
