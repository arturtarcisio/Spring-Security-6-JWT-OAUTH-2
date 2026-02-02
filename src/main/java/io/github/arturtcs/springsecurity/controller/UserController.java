package io.github.arturtcs.springsecurity.controller;

import io.github.arturtcs.springsecurity.dto.NewUserDTO;
import io.github.arturtcs.springsecurity.entities.User;
import io.github.arturtcs.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        userService.newUser(newUserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUser() {
        return ResponseEntity.ok(userService.findAll());
    }
}
