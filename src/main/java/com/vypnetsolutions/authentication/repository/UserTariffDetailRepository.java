package com.vypnetsolutions.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vypnetsolutions.authentication.tariffdata.UserTariffDetail;

@Repository
public interface UserTariffDetailRepository extends JpaRepository<UserTariffDetail, Long> {

    Optional<UserTariffDetail> findByUserId(Long userId);

}
