package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.dto.CreateDonationRequest;
import com.proy.backend_donaciones.domain.service.DonationService;
import com.proy.backend_donaciones.persistence.entity.Donacion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<Donation> create(@RequestBody CreateDonationRequest request) {
        Donation createdDonation = donationService.createDonation(request);
        return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
    }

   @GetMapping("/my")
public List<Donation> getMyDonations() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    System.out.println("🔍 Email autenticado: " + email);
    return donationService.getMyDonationsByEmail(email);
}
    @GetMapping("/{id}")
    public ResponseEntity<Donation> getById(@PathVariable Long id) {
        return donationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Donation> getAll() {
        return donationService.getAll();
    }
    @PutMapping("/cambiar-estado/{id}")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public ResponseEntity<?> cambiarEstado(
  @PathVariable Long id,
  @RequestParam("estado") String estadoNuevo) {
    try {
        // Convertir String a Enum
        Donacion.EstadoDonacion nuevoEstado = Donacion.EstadoDonacion.valueOf(estadoNuevo.toUpperCase());

        Donacion donacion = donationService.cambiarEstado(id, nuevoEstado);

        return ResponseEntity.ok("Estado actualizado a " + nuevoEstado);

    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Estado inválido: " + estadoNuevo);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donación no encontrada");
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return donationService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
