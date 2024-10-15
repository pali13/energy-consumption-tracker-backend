package com.vypnetsolutions.authentication.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vypnetsolutions.authentication.entity.DailyConsumption;
import com.vypnetsolutions.authentication.websocket.ConsumptionWebSocketHandler;

@Service
public class ConsumptionService {
    @Autowired
    private ConsumptionWebSocketHandler webSocketHandler;

    @Autowired
    private DailyConsumptionService dailyConsumptionService;

    @Scheduled(initialDelay = 30000, fixedRate = 60000)
public void sendConsumptionData() throws IOException {
    Map<Long, Double> consumptionDataByUser = new HashMap<>();
    
    // Iterar sobre los usuarios en userSessionIds
    for (Long userId : webSocketHandler.getUserSessionIds().keySet()) {
        double consumption = getTotalConsumptionByUser(userId);
        consumptionDataByUser.put(userId, consumption);
    }
    
    // Broadcast de los datos de consumo
    webSocketHandler.broadcastConsumptionData(consumptionDataByUser);
}

    private double getTotalConsumptionByUser(Long userId) {
        List<DailyConsumption> dailyConsumptions = dailyConsumptionService.findByUserIdAndDate(userId, LocalDate.now());
        if (dailyConsumptions == null || dailyConsumptions.isEmpty()) {
            return 0;
        }
        return dailyConsumptions.stream()
                .mapToDouble(DailyConsumption::getConsumption)
                .sum();
    }
}