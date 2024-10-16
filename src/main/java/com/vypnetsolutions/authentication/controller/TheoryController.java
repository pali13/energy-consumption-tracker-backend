package com.vypnetsolutions.authentication.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vypnetsolutions.authentication.dto.TheoryDTO;
import com.vypnetsolutions.authentication.entity.Theory;
import com.vypnetsolutions.authentication.service.TheoryService;

@RestController
@RequestMapping("/api/theory")
public class TheoryController {
    @Autowired
    private TheoryService theoryService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTheoryById(@PathVariable Long id) {
        Optional<Theory> theory = theoryService.getTheoryById(id);
        return theory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/{id}")
    public ResponseEntity<TheoryDTO> saveTheory(@PathVariable Long id, @RequestBody Theory theory) {
        System.out.println("id: " + id);
        System.out.println("theory: " + theory);
        Optional<Theory> theoryOptional = theoryService.getTheoryById(id);
        if (theoryOptional.isPresent()) {
            Theory theorySaved = theoryOptional.get();
            theorySaved.setText(theory.getText());
            Theory theorySavedNew = theoryService.saveTheory(theorySaved);
            TheoryDTO theoryDTO = new TheoryDTO(theorySavedNew);
            theoryDTO.setId(theorySavedNew.getId());
            theoryDTO.setText(theorySavedNew.getText());
            return ResponseEntity.ok(theoryDTO);
        } else {
            Theory theorySaved = theoryService.saveTheory(theory);
            TheoryDTO theoryDTO = new TheoryDTO(theorySaved);
            theoryDTO.setId(theorySaved.getId());
            theoryDTO.setText(theorySaved.getText());
            return ResponseEntity.ok(theoryDTO);
        }
    }
}
