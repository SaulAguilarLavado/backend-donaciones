package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.dto.CreateDonationRequest;
import com.proy.backend_donaciones.domain.repository.DonationRepository;
import com.proy.backend_donaciones.domain.repository.UserRepository;

import com.proy.backend_donaciones.persistence.entity.Donacion;
import com.proy.backend_donaciones.persistence.entity.Donacion.EstadoDonacion;
import com.proy.backend_donaciones.persistence.DonacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    // Repositorio del dominio (trabaja con domain.Donation)
    @Autowired 
    private DonationRepository donationRepository;

    @Autowired 
    private UserRepository userRepository;

    // Servicio para registrar puntos
    @Autowired 
    private PuntoUsuarioService puntoUsuarioService;

    // Repositorio de la entidad JPA
    @Autowired
    private DonacionRepository donacionEntityRepository;


    // ---------------------- CRUD DOMAIN ----------------------

    public List<Donation> getAll() {
        return donationRepository.getAll();
    }

    public Optional<Donation> getById(Long id) {
        return donationRepository.findById(id);
    }

    public Donation save(Donation donation) {

        // Obtener usuario autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User donor = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        donation.setDonorId(donor.getUserId());
        donation.setCreationDate(LocalDateTime.now());
        donation.setStatus("PENDIENTE");

        return donationRepository.save(donation);
    }

    public boolean delete(Long id) {
        return getById(id).map(donation -> {
            donationRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public List<Donation> getMyDonations(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    public List<Donation> getMyDonationsByEmail(String email) {
        return donationRepository.findByUsuarioEmail(email);
    }


    // ---------------------- CAMBIO DE ESTADO (ENTITY JPA) ----------------------

    public Donacion cambiarEstado(Long id, EstadoDonacion nuevoEstado) {

        // 1. Obtener la entidad real desde la BD
        Donacion donacion = donacionEntityRepository.findEntityById(id)
                .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

        // 2. Cambiar estado
        donacion.setEstado(nuevoEstado);
        donacionEntityRepository.saveEntity(donacion);

        // 3. Si COMPLETADA → asignar puntos
        if (nuevoEstado == EstadoDonacion.COMPLETADA) {
            puntoUsuarioService.asignarPuntos(
                donacion.getDonante().getId(), 
                10,
                "Donación completada ID #" + donacion.getId()
            );
        }

        return donacion;
    }


    // ---------------------- CREAR DONACIÓN DOMAIN ----------------------

    public Donation createDonation(CreateDonationRequest request) {

        Donation donation = new Donation();
        donation.setFoodCategory(request.getFoodCategory());
        donation.setDescription(request.getDescription());
        donation.setApproximateQuantity(request.getApproximateQuantity());
        donation.setUnit(request.getUnit());
        donation.setPickupAddress(request.getPickupAddress());
        donation.setPickupDate(request.getPickupDate());
        donation.setPickupTime(request.getPickupTime());
        donation.setContactPhone(request.getContactPhone());
        donation.setAssignmentType(request.getAssignmentType());
        donation.setBeneficiaryId(request.getBeneficiaryId());
        donation.setBeneficiaryType(request.getBeneficiaryType());
        donation.setConsumable(request.isConsumable());

        return save(donation);
    }
}
