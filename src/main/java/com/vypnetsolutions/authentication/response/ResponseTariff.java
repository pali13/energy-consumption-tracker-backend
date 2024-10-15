package com.vypnetsolutions.authentication.response;

import com.vypnetsolutions.authentication.tariffdata.TariffType;

public class ResponseTariff {
    private Long id;
    private String peakStartTime;
    private String peakEndTime;
    private Long tariffId;
    private Long userId;
    private TariffType type;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public Long getTariffId(){
        return tariffId;
    }

    public void setTariffId(Long tariffId){
        this.tariffId = tariffId;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public TariffType getType(){
        return type;
    }

    public void setType(TariffType type){
        this.type = type;
    }
} 
