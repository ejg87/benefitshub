package com.benefitshub.api.controller;

import com.benefitshub.api.model.Benefit;
import com.benefitshub.api.security.UserDetailsImpl;
import com.benefitshub.api.service.BenefitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benefits")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BenefitController {

    @Autowired
    private BenefitService benefitService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Benefit>> getAllBenefits() {
        return ResponseEntity.ok(benefitService.getAllBenefits());
    }

    @GetMapping("/my-benefits")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<List<Benefit>> getMyBenefits() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(benefitService.getBenefitsByUserId(userDetails.getId()));
    }

    @GetMapping("/my-benefits/active")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<List<Benefit>> getMyActiveBenefits() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(benefitService.getActiveBenefitsByUserId(userDetails.getId()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Benefit> createBenefit(@Valid @RequestBody Benefit benefit) {
        return ResponseEntity.ok(benefitService.createBenefit(benefit));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Benefit> updateBenefit(@PathVariable Long id,
                                                 @Valid @RequestBody Benefit benefit) {
        return ResponseEntity.ok(benefitService.updateBenefit(id, benefit));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBenefit(@PathVariable Long id) {
        benefitService.deleteBenefit(id);
        return ResponseEntity.ok("Benefit deleted successfully");
    }
}