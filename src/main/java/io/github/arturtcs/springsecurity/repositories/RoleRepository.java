package io.github.arturtcs.springsecurity.repositories;

import io.github.arturtcs.springsecurity.entities.Role;
import io.github.arturtcs.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
