package com.vypnetsolutions.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.tariffdata.TariffType;


@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Optional<Tariff> findByType(TariffType type);
}
