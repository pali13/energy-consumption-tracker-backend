package com.vypnetsolutions.authentication.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.repository.TariffRepository;

import jakarta.transaction.Transactional;

@Service
public class TariffService {
    @Autowired
    private TariffRepository tariffRepository;

    // @Autowired
    // private UserRepository userRepository;

    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    @Transactional
    public Optional<Tariff> getTariffById(Long id) {
        Optional<Tariff> tariff = tariffRepository.findById(id);
        tariff.ifPresent(t -> Hibernate.initialize(t.getDetails()));
        return tariff;
    }

    public Tariff saveTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    public void deleteTariff(Long id) {
        tariffRepository.deleteById(id);
    }

    // public Tariff setPeakHours(Long id, String peakHours) {
    //     Tariff tariff = getTariffById(id);
    //     if (tariff != null && (tariff.getType() == TariffType.DOUBLE_HOURLY || tariff.getType() == TariffType.TRIPLE_HOURLY)) {
    //         if (isValidPeakHours(peakHours)) {
    //             tariff.setPeakHours(peakHours);
    //             return saveTariff(tariff);
    //         } else {
    //             throw new IllegalArgumentException("Invalid peak hours");
    //         }
    //     }
    //     throw new IllegalArgumentException("Tariff not found or invalid tariff type");
    // }

    // private boolean isValidPeakHours(String peakHours) {
    //     String[] hours = peakHours.split("-");
    //     if (hours.length == 2) {
    //         try {
    //             int start = Integer.parseInt(hours[0]);
    //             int end = Integer.parseInt(hours[1]);
    //             return start >= 17 && end <= 23 && (end - start == 4);
    //         } catch (NumberFormatException e) {
    //             return false;
    //         }
    //     }
    //     return false;
    // }
    
    // public Tariff saveTariffForUser(Long userId, Tariff tariff) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
                
    //     return tariffRepository.save(tariff);
    // }
}