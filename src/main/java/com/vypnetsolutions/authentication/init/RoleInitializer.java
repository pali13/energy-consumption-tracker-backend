package com.vypnetsolutions.authentication.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.vypnetsolutions.authentication.entity.Role;
import com.vypnetsolutions.authentication.entity.Role.ERole;
import com.vypnetsolutions.authentication.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (ERole role : ERole.values()) {
            Optional<Role> existingRole = roleRepository.findByName(role);
            if (existingRole.isEmpty()) {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            }
        }
    }
}

