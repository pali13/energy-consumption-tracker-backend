package com.vypnetsolutions.authentication.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vypnetsolutions.authentication.dto.TariffChangeRequest;
import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.repository.UserTariffDetailRepository;
import com.vypnetsolutions.authentication.response.ResponseTariff;
import com.vypnetsolutions.authentication.service.TariffService;
import com.vypnetsolutions.authentication.service.UserService;
import com.vypnetsolutions.authentication.tariffdata.UserTariffDetail;

@RestController
@RequestMapping("/api/users/{userId}/tariff")
public class TariffController {
    @Autowired
    private TariffService tariffService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTariffDetailRepository userTariffDetailRepository;

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @GetMapping("/{id}")
    public Tariff getTariff(@PathVariable Long id) {
        Optional<Tariff> simpleTariff = tariffService.getTariffById(id);
        return simpleTariff.orElseThrow(() -> new IllegalArgumentException("Tariff not found with id: " + id));
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @PostMapping
    public Tariff saveTariff(@RequestBody Tariff tariff) {
        return tariffService.saveTariff(tariff);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @DeleteMapping("/{id}")
    public void deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @GetMapping
    public ResponseTariff getTariffByUser(@PathVariable Long userId) {
        UserTariffDetail userTariffDetail = userTariffDetailRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Tariff not found with userId: " + userId));
        ResponseTariff tariff = new ResponseTariff();
        tariff.setId(userTariffDetail.getId());
        tariff.setPeakStartTime(userTariffDetail.getPeakStartTime());
        tariff.setPeakEndTime(userTariffDetail.getPeakEndTime());
        tariff.setTariffId(userTariffDetail.getTariff().getId());
        tariff.setType(userTariffDetail.getTariff().getType());
        tariff.setUserId(userTariffDetail.getUser().getId());
        return tariff;
    }

    @CrossOrigin(origins = "https://los-electricos-5toc3.onrender.com")
    @PostMapping("/changeTariff")
    public ResponseTariff changeUserTariff(@PathVariable Long userId,
            @RequestBody TariffChangeRequest tariffChangeRequest) {
        ResponseTariff tariff = userService.changeUserTariff(userId, tariffChangeRequest);
        return tariff;
    }
}
