package com.vypnetsolutions.authentication.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import com.vypnetsolutions.authentication.entity.Appliance;

public class ApplianceDTO {
    private Long id;
    private String name;
    private String type;
    private double powerRating;
    private boolean status;
    private Long userId;
    private String location;
    private OffsetDateTime lastOnTime;
    private OffsetDateTime lastOffTime;
    private double dailyUsage;
    private double peakUsage;
    private double offPeakUsage;
    private double totalUsage;
    private List<Long> dailyConsumptions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ApplianceDTO() {

    }

    public ApplianceDTO(Appliance appliance) {
        this.id = appliance.getId();
        this.name = appliance.getName();
        this.type = appliance.getType();
        this.powerRating = appliance.getPowerRating();
        this.status = appliance.isStatus();
        this.location = appliance.getLocation();
        this.lastOnTime = appliance.getLastOnTime();
        this.lastOffTime = appliance.getLastOffTime();
        this.dailyUsage = appliance.getDailyUsage();
        this.peakUsage = appliance.getPeakUsage();
        this.offPeakUsage = appliance.getOffPeakUsage();
        this.totalUsage = appliance.getTotalUsage();
        this.createdAt = appliance.getCreatedAt();
        this.updatedAt = appliance.getUpdatedAt();
        this.userId = appliance.getUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPowerRating() {
        return powerRating;
    }

    public void setPowerRating(double powerRating) {
        this.powerRating = powerRating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OffsetDateTime getLastOnTime() {
        return lastOnTime;
    }

    public void setLastOnTime(OffsetDateTime lastOnTime) {
        this.lastOnTime = lastOnTime;
    }

    public OffsetDateTime getLastOffTime() {
        return lastOffTime;
    }

    public void setLastOffTime(OffsetDateTime lastOffTime) {
        this.lastOffTime = lastOffTime;
    }

    public double getDailyUsage() {
        return dailyUsage;
    }

    public void setDailyUsage(double dailyUsage) {
        this.dailyUsage = dailyUsage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPeakUsage() {
        return peakUsage;
    }

    public void setPeakUsage(double peakUsage) {
        this.peakUsage = peakUsage;
    }

    public double getOffPeakUsage() {
        return offPeakUsage;
    }

    public void setOffPeakUsage(double offPeakUsage) {
        this.offPeakUsage = offPeakUsage;
    }

    public double getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(double totalUsage) {
        this.totalUsage = totalUsage;
    }

    public List<Long> getDailyConsumptions() {
        return dailyConsumptions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
