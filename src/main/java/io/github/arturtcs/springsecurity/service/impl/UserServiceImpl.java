package io.github.arturtcs.springsecurity.service.impl;

import io.github.arturtcs.springsecurity.dto.NewUserDTO;
import io.github.arturtcs.springsecurity.entities.Role;
import io.github.arturtcs.springsecurity.entities.User;
import io.github.arturtcs.springsecurity.repositories.RoleRepository;
import io.github.arturtcs.springsecurity.repositories.UserRepository;
import io.github.arturtcs.springsecurity.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void newUser(NewUserDTO newUserDTO) {

        var roleBasic = roleRepository.findByName(Role.Values.BASIC.name());
        var userFromDB = userRepository.findByUsername(newUserDTO.username());

        userFromDB.ifPresentOrElse(
                user -> {

                },
                () -> {
                    var user = new User();
                    user.setUsername(newUserDTO.username());
                    user.setPassword(bCryptPasswordEncoder.encode(newUserDTO.password()));
                    user.setRoles(Set.of(roleBasic));
                    userRepository.save(user);
                }
        );
    }
}
