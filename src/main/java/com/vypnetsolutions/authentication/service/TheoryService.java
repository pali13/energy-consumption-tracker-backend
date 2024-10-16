package com.vypnetsolutions.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vypnetsolutions.authentication.entity.Theory;
import com.vypnetsolutions.authentication.repository.TheoryRepository;

@Service
public class TheoryService {
    @Autowired
    TheoryRepository theoryRepository;

    public Optional<Theory> getTheoryById(Long id){
        return theoryRepository.findById(id);
    }

    public Theory saveTheory(Theory theory) {
        return theoryRepository.save(theory);
    }

}
