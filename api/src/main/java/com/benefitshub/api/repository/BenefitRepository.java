package com.benefitshub.api.repository;

import com.benefitshub.api.model.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    List<Benefit> findByUserId(Long userId);

    List<Benefit> findByUserIdAndActive(Long userId, boolean active);

    List<Benefit> findByActive(boolean active);
}