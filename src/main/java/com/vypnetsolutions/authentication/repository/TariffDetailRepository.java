package com.vypnetsolutions.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vypnetsolutions.authentication.tariffdata.TariffDetail;
import com.vypnetsolutions.authentication.tariffdata.TariffType;

public interface TariffDetailRepository extends JpaRepository<TariffDetail, Long> {
    List<TariffDetail> findByTariffType(TariffType type);
}