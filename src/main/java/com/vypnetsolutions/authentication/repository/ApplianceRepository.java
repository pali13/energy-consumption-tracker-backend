package com.vypnetsolutions.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.entity.Appliance;
import com.vypnetsolutions.authentication.entity.User;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    List<Appliance> findByUser(User user);
}