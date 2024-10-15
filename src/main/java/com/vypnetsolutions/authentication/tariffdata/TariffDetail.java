package com.vypnetsolutions.authentication.tariffdata;

import com.vypnetsolutions.authentication.entity.Tariff;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TariffDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String period;
    private double pricePerKwh;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    // @OneToMany(mappedBy = "tariffDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<UserTariffDetail> userTariffDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period){
        this.period = period;
    }

    public double getPricePerKwh() {
        return pricePerKwh;
    }

    public void setPricePerKwh(double pricePerKwh){
        this.pricePerKwh = pricePerKwh;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff){
        this.tariff = tariff;
    }

    // public List<UserTariffDetail> getUserTariffDetails(){
    //     return userTariffDetails;
    // }

    // public void setUserTariffDetails(UserTariffDetail userTariffDetail){
    //     this.userTariffDetails.add(userTariffDetail);
    // }
}