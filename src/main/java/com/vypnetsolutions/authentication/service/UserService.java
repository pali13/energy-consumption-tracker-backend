package com.vypnetsolutions.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vypnetsolutions.authentication.dto.TariffChangeRequest;
import com.vypnetsolutions.authentication.entity.Tariff;
import com.vypnetsolutions.authentication.entity.User;
import com.vypnetsolutions.authentication.repository.TariffRepository;
import com.vypnetsolutions.authentication.repository.UserRepository;
import com.vypnetsolutions.authentication.repository.UserTariffDetailRepository;
import com.vypnetsolutions.authentication.response.ResponseTariff;
import com.vypnetsolutions.authentication.tariffdata.UserTariffDetail;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private UserTariffDetailRepository userTariffDetailRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public ResponseTariff changeUserTariff(Long userId, TariffChangeRequest tariffChangeRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Optional<Tariff> tariffOptional = tariffRepository.findById(tariffChangeRequest.getTariffId());
        if (!tariffOptional.isPresent()) {
            throw new RuntimeException("Tariff not found with id: " + tariffChangeRequest.getTariffId());
        }

        User user = userOptional.get();
        Tariff tariff = tariffOptional.get();

        // Actualiza o crea el UserTariffDetail correspondiente
        UserTariffDetail userTariffDetail = userTariffDetailRepository.findByUserId(userId)
                .orElse(new UserTariffDetail());
        userTariffDetail.setUser(user);
        userTariffDetail.setTariff(tariff);
        userTariffDetail.setPeakStartTime(tariffChangeRequest.getPeakStartTime());
        userTariffDetail.setPeakEndTime(tariffChangeRequest.getPeakEndTime());

        userTariffDetailRepository.save(userTariffDetail);

        ResponseTariff responseTariff = new ResponseTariff();
        responseTariff.setId(userTariffDetail.getId());
        responseTariff.setPeakStartTime(userTariffDetail.getPeakStartTime());
        responseTariff.setPeakEndTime(userTariffDetail.getPeakEndTime());
        responseTariff.setTariffId(userTariffDetail.getTariff().getId());
        responseTariff.setType(userTariffDetail.getTariff().getType());
        responseTariff.setUserId(userTariffDetail.getUser().getId());

        return responseTariff;
    }
}
