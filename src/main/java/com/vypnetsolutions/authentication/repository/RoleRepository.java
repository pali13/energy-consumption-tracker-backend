package com.vypnetsolutions.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.vypnetsolutions.authentication.entity.Role;
import com.vypnetsolutions.authentication.entity.Role.ERole;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}