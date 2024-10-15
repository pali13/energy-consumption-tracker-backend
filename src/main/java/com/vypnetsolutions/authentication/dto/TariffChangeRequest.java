package com.vypnetsolutions.authentication.dto;

public class TariffChangeRequest {
    private Long tariffId;
    private String peakStartTime;
    private String peakEndTime;

    public Long getTariffId(){
        return tariffId;
    }

    public void setTariffId(Long tariffId){
        this.tariffId = tariffId;
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