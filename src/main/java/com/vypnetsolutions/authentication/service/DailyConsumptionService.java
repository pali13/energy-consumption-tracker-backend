package com.vypnetsolutions.authentication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vypnetsolutions.authentication.entity.DailyConsumption;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.repository.DailyConsumptionRepository;

@Service
public class DailyConsumptionService {
    @Autowired
    private DailyConsumptionRepository dailyConsumptionRepository;
    @Autowired

    public List<DailyConsumption> findAll() {
        return dailyConsumptionRepository.findAll();
    }

    public List<DailyConsumption> findByUserIdAndDate(Long userId, LocalDate date) {
        return dailyConsumptionRepository.findByUserIdAndDate(userId, date);
    }

    public List<DailyConsumption> findByDateAndUser(LocalDate date, User user) {
        return dailyConsumptionRepository.findByDateAndUser(date, user);
    }

    public List<DailyConsumption> findAllByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId) {
        return dailyConsumptionRepository.findAllByDateBetweenAndUserId(startDate, endDate, userId);
    }

    public DailyConsumption saveOrUpdateDailyConsumption(DailyConsumption dailyConsumption) {
        Optional<DailyConsumption> existingConsumptionOptional = dailyConsumptionRepository
        .findByUserIdAndApplianceIdAndDate(dailyConsumption.getUser().getId(), dailyConsumption.getAppliance().getId(), dailyConsumption.getDate());
    
        DailyConsumption existingConsumption;
        if (existingConsumptionOptional.isPresent()) {
            // Si ya existe un consumo para ese usuario, ese dispositivo, en esa fecha, lo actualiza
            existingConsumption = existingConsumptionOptional.get();
            existingConsumption.setConsumption(dailyConsumption.getConsumption() + existingConsumption.getConsumption());
            existingConsumption.setPeakUsage(dailyConsumption.getPeakUsage() + existingConsumption.getPeakUsage());
            existingConsumption.setMidPeakUsage(dailyConsumption.getMidPeakUsage() + existingConsumption.getMidPeakUsage());
            existingConsumption.setOffPeakUsage(dailyConsumption.getOffPeakUsage() + existingConsumption.getOffPeakUsage());
        } else {
            // Si no existe, crea uno nuevo
            existingConsumption = dailyConsumption;
        }
    
        return dailyConsumptionRepository.save(existingConsumption);
    }
    

    public void deleteDailyConsumption(Long id) {
        dailyConsumptionRepository.deleteById(id);
    }

    // @Scheduled(cron = "0 0 0 * * ?")
    // public void saveDailyConsumption() {
    //     List<Appliance> appliances = applianceService.getAllAppliances();

    //     for (Appliance appliance : appliances) {
    //         // Guardar el consumo diario
    //         DailyConsumption dailyConsumption = new DailyConsumption();
    //         dailyConsumption.setAppliance(appliance);
    //         dailyConsumption.setDate(LocalDate.now());
    //         dailyConsumption.setMidPeakUsage(appliance.getMidPeakUsage());
    //         dailyConsumption.setPeakUsage(appliance.getPeakUsage());
    //         dailyConsumption.setOffPeakUsage(appliance.getOffPeakUsage());
    //         dailyConsumptionRepository.save(dailyConsumption);

    //         // Reiniciar los contadores
    //         appliance.setDailyUsage(0);
    //         appliance.setMidPeakUsage(0);
    //         appliance.setPeakUsage(0);
    //         appliance.setOffPeakUsage(0);
    //         applianceService.updateAppliance(appliance);
    //     }
    // }

}
