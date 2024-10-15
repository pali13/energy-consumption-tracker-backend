package com.vypnetsolutions.authentication.entity;

import java.util.ArrayList;
import java.util.List;

import com.vypnetsolutions.authentication.tariffdata.TariffDetail;
import com.vypnetsolutions.authentication.tariffdata.TariffType;

import jakarta.persistence.*;

@Entity
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TariffType type;
    
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffDetail> details;

    public Tariff() {
        this.details = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TariffType getType() {
        return type;
    }

    public void setType(TariffType type) {
        this.type = type;
    }

    public List<TariffDetail> getDetails() {
        return details;
    }

    public void setDetails(TariffDetail detail) {
        if (details == null) {
            details = new ArrayList<>();
        }
        this.details.add(detail);
        detail.setTariff(this);
    }
    
    public void addDetail(TariffDetail detail){
        this.details.add(detail);
    }
}
