package com.vypnetsolutions.authentication.tariffdata;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserTariffDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-tariffDetails")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "tariff_id", nullable = false)
    @JsonBackReference(value = "tariff-tariffDetails")
    private Tariff tariff;

    private String peakStartTime;  // hora de inicio de punta específica del usuario
    private String peakEndTime;    // hora de fin de punta específica del usuario

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Tariff getTariff(){
        return tariff;
    }

    public void setTariff(Tariff tariff){
        this.tariff = tariff;
    }

    public String getPeakStartTime(){
        return peakStartTime;
    }

    public void setPeakStartTime(String peakStartTime){
        this.peakStartTime = peakStartTime;
    }

    public String getPeakEndTime(){
        return peakEndTime;
    }

    public void setPeakEndTime(String peakEndTime){
        this.peakEndTime = peakEndTime;
    }

}
