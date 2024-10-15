package com.vypnetsolutions.authentication.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DailyConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;  // Utilizamos LocalDate para solo tener día, mes y año
    private double consumption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-dailyConsumptions")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "appliance_id")
    @JsonBackReference(value = "appliance-dailyConsumptions")
    private Appliance appliance;

    private double peakUsage; // en kWh
    private double midPeakUsage; // en kWh
    private double offPeakUsage; // en kWh

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    public double getPeakUsage(){
        return peakUsage;
    }

    public void setPeakUsage(double peakUsage){
        this.peakUsage = peakUsage;
    }

    public double getMidPeakUsage(){
        return midPeakUsage;
    }

    public void setMidPeakUsage(double midPeakUsage){
        this.midPeakUsage = midPeakUsage;
    }

    public double getOffPeakUsage(){
        return offPeakUsage;
    }

    public void setOffPeakUsage(double offPeakUsage){
        this.offPeakUsage = offPeakUsage;
    }
}
