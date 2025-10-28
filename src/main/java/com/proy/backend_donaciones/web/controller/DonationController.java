package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.dto.CreateDonationRequest;
import com.proy.backend_donaciones.domain.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // Endpoint para el botón "CONFIRMAR" del paso 4
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Donation> create(@RequestBody CreateDonationRequest request) {
        Donation createdDonation = donationService.createDonation(request);
        return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
    }

    // Endpoint para la página "Estado de Pedido"
    @GetMapping("/my-history")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Donation>> getMyHistory() {
        List<Donation> userDonations = donationService.findDonationsByCurrentUser();
        return ResponseEntity.ok(userDonations);
    }
}