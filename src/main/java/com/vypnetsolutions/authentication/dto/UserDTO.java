package com.vypnetsolutions.authentication.dto;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private LocalDate birthDate;

    public UserDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthgetBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
