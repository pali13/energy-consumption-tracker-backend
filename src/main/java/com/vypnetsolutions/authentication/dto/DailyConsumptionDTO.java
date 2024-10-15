package com.vypnetsolutions.authentication.dto;

import java.time.LocalDate;

public class DailyConsumptionDTO {
    private Long id;
    private LocalDate date;
    private double consumption;
    private Long applianceId;
    private Long userId;
    private double peakUsage; // en kWh
    private double midPeakUsage; // en kWh
    private double offPeakUsage; // en kWh

    public Long getId() {
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

    public Long getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(Long applianceId) {
        this.applianceId = applianceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setMidPeakUsage(double midUsagePeak){
        this.midPeakUsage = midUsagePeak;
    }

    public double getOffPeakUsage(){
        return offPeakUsage;
    }

    public void setOffPeakUsage(double offPeakUsage){
        this.offPeakUsage = offPeakUsage;
    }

}
