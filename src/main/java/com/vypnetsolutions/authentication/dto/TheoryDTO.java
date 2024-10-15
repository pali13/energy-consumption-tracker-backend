package com.vypnetsolutions.authentication.dto;

import com.vypnetsolutions.authentication.entity.Theory;

public class TheoryDTO {
    private Long id;
    private String text;

    public TheoryDTO() {

    }

    public TheoryDTO(Theory theory) {
        this.id = theory.getId();
        this.text = theory.getText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
