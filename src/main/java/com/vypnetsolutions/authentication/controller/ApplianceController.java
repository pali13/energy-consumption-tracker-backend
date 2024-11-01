package com.vypnetsolutions.authentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vypnetsolutions.authentication.dto.ApplianceDTO;
import com.vypnetsolutions.authentication.entity.Appliance;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.service.ApplianceService;
import com.vypnetsolutions.authentication.service.UserService;

@RestController
@RequestMapping("/api/users/{userId}/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com:8081")
    @GetMapping
    public List<Appliance> getAppliancesByUserId(@PathVariable Long userId) {
        return applianceService.getAllAppliancesByUserId(userId);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com:8081")
    @GetMapping("/{id}")
    public ResponseEntity<Appliance> getApplianceById(@PathVariable Long id) {
        Optional<Appliance> appliance = applianceService.getApplianceById(id);
        return appliance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com:8081")
    @PutMapping("/{id}")
    public ResponseEntity<ApplianceDTO> updateAppliance(@PathVariable Long userId, @PathVariable Long id, @RequestBody Appliance appliance) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userId));
        appliance.setUser(user);
        Appliance saved = applianceService.saveAppliance(appliance);
        ApplianceDTO applianceDTO = new ApplianceDTO(saved);
        applianceDTO.setId(saved.getId());
        applianceDTO.setName(saved.getName());
        applianceDTO.setType(saved.getType());
        applianceDTO.setPowerRating(saved.getPowerRating());
        applianceDTO.setLocation(saved.getLocation());
        applianceDTO.setDailyUsage(saved.getDailyUsage());
        applianceDTO.setLastOffTime(saved.getLastOffTime());
        applianceDTO.setLastOnTime(saved.getLastOnTime());
        applianceDTO.setOffPeakUsage(saved.getOffPeakUsage());
        applianceDTO.setPeakUsage(saved.getPeakUsage());
        applianceDTO.setStatus(saved.isStatus());
        applianceDTO.setTotalUsage(saved.getTotalUsage());
        applianceDTO.setUserId(saved.getUser().getId());
        return ResponseEntity.ok(applianceDTO);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com:8081")
    @PostMapping
    public ResponseEntity<ApplianceDTO> createAppliance(@PathVariable Long userId, @RequestBody Appliance appliance) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userId));
        appliance.setUser(user);
        Appliance saved = applianceService.saveAppliance(appliance);
        ApplianceDTO applianceDTO = new ApplianceDTO();
        applianceDTO.setId(saved.getId());
        applianceDTO.setName(saved.getName());
        applianceDTO.setType(saved.getType());
        applianceDTO.setPowerRating(saved.getPowerRating());
        applianceDTO.setLocation(saved.getLocation());
        return ResponseEntity.ok(applianceDTO);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com:8081")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppliance(@PathVariable Long id) {
        applianceService.deleteAppliance(id);
        return ResponseEntity.noContent().build();
    }

}
