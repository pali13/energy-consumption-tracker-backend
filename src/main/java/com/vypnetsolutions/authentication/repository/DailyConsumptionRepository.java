package com.vypnetsolutions.authentication.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.entity.DailyConsumption;
import com.vypnetsolutions.authentication.entity.User;

@Repository
public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption, Long> {
    @SuppressWarnings("null")
    @Override
	List<DailyConsumption> findAll();

    @SuppressWarnings("null")
    @Override
    Optional<DailyConsumption> findById(Long id);
    
    List<DailyConsumption> findByUserIdAndDate(Long userId, LocalDate date);

	List<DailyConsumption> getDailyConsumptionByUser(User user);

    public void deleteByDate(LocalDate date);

    List<DailyConsumption> findByDateAndUser(LocalDate date, User user);

    List<DailyConsumption>  findAllByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);

    Optional<DailyConsumption> findByUserIdAndApplianceIdAndDate(Long userId, Long applianceId, LocalDate date);

}