package com.vypnetsolutions.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.vypnetsolutions.authentication.entity.Appliance;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.repository.ApplianceRepository;
import com.vypnetsolutions.authentication.repository.UserRepository;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Appliance> getAllAppliancesByUserId(Long userId) {
        // Buscar el usuario por su ID
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        // Devolver los aparatos asociados al usuario
        return applianceRepository.findByUser(user);
    }

    public Optional<Appliance> getApplianceById(Long id) {
        return applianceRepository.findById(id);
    }

    // public List<Appliance> getAppliancesById(Long id) {
    //     Optional<User> userOptional = userRepository.findById(id);
    //     if (userOptional.isPresent()) {
    //         User user = userOptional.get();
    //         return applianceRepository.findByUserId(user.getId());
    //     } else {
    //         throw new RuntimeException("User not found with id: " + id);
    //     }
    // }

    public Appliance updateAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
        // Optional<Appliance> optionalAppliance = applianceRepository.findById(id);
        // if (!optionalAppliance.isPresent()) {
        //     return null;
        // }
        // Appliance appliance = optionalAppliance.get();
        // appliance.setName(applianceDTO.getName());
        // appliance.setType(applianceDTO.getType());
        // appliance.setPowerRating(applianceDTO.getPowerRating());
        // appliance.setStatus(applianceDTO.isStatus());
        // appliance.setLocation(applianceDTO.getLocation());
        // appliance.setDailyUsage(applianceDTO.getDailyUsage());
        // appliance.setPeakUsage(applianceDTO.getPeakUsage());
        // appliance.setOffPeakUsage(applianceDTO.getOffPeakUsage());
        // appliance.setTotalUsage(applianceDTO.getTotalUsage());
        // appliance.setUpdatedAt(LocalDateTime.now());

        // applianceRepository.save(appliance);

        // return new ApplianceDTO(appliance);
    }

    public Appliance saveAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
    }

    public void deleteAppliance(Long id) {
        applianceRepository.deleteById(id);
    }

    // Puedes agregar más métodos según las necesidades de tu lógica de negocio
}