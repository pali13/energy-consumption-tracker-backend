package com.vypnetsolutions.authentication.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vypnetsolutions.authentication.dto.DailyConsumptionDTO;
import com.vypnetsolutions.authentication.entity.DailyConsumption;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.service.DailyConsumptionService;
import com.vypnetsolutions.authentication.service.UserService;

@RestController
@RequestMapping("/api/users/{userId}/consumptions")
public class DailyConsumptionController {
    @Autowired
    private DailyConsumptionService dailyConsumptionService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @GetMapping("/{date}")
    public ResponseEntity<List<DailyConsumption>> getTodayConsumption(@PathVariable User userId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(dailyConsumptionService.findByDateAndUser(date, userId));
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @PostMapping
    public ResponseEntity<DailyConsumptionDTO> saveConsumption(@PathVariable Long userId, @RequestBody DailyConsumption dailyConsumption) {
        User user = userService.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userId));
        dailyConsumption.setUser(user);
        DailyConsumption saved = dailyConsumptionService.saveOrUpdateDailyConsumption(dailyConsumption);
        DailyConsumptionDTO dailyConsumptionDTO = new DailyConsumptionDTO();
        dailyConsumptionDTO.setApplianceId(saved.getAppliance().getId());
        dailyConsumptionDTO.setConsumption(saved.getConsumption());
        dailyConsumptionDTO.setPeakUsage(saved.getPeakUsage());
        dailyConsumptionDTO.setMidPeakUsage(saved.getMidPeakUsage());
        dailyConsumptionDTO.setOffPeakUsage(saved.getOffPeakUsage());
        dailyConsumptionDTO.setDate(saved.getDate());
        dailyConsumptionDTO.setId(saved.getId());
        dailyConsumptionDTO.setUserId(userId);
        return ResponseEntity.ok(dailyConsumptionDTO);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @GetMapping
    public ResponseEntity<List<DailyConsumption>> getConsumptionsBetweenDates(
            @PathVariable Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(dailyConsumptionService.findAllByDateBetweenAndUserId(startDate, endDate, userId));
    }
}
