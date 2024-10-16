package com.vypnetsolutions.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @SuppressWarnings("null")
    @Override
    Optional<User> findById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
