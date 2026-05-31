package com.benefitshub.api.service;

import com.benefitshub.api.model.Benefit;
import com.benefitshub.api.repository.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefitService {

    @Autowired
    private BenefitRepository benefitRepository;

    public List<Benefit> getAllBenefits() {
        return benefitRepository.findAll();
    }

    public List<Benefit> getBenefitsByUserId(Long userId) {
        return benefitRepository.findByUserId(userId);
    }

    public List<Benefit> getActiveBenefitsByUserId(Long userId) {
        return benefitRepository.findByUserIdAndActive(userId, true);
    }

    public Benefit createBenefit(Benefit benefit) {
        return benefitRepository.save(benefit);
    }

    public Benefit updateBenefit(Long id, Benefit updatedBenefit) {
        Benefit benefit = benefitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found with id: " + id));

        benefit.setName(updatedBenefit.getName());
        benefit.setDescription(updatedBenefit.getDescription());
        benefit.setType(updatedBenefit.getType());
        benefit.setEnrollmentDate(updatedBenefit.getEnrollmentDate());
        benefit.setExpirationDate(updatedBenefit.getExpirationDate());
        benefit.setActive(updatedBenefit.isActive());

        return benefitRepository.save(benefit);
    }

    public void deleteBenefit(Long id) {
        Benefit benefit = benefitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found with id: " + id));
        benefitRepository.delete(benefit);
    }
}