package com.vypnetsolutions.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.entity.Theory;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, Long>{
    
}
