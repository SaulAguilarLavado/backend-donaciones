package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.dto.CreateDonationRequest;
import com.proy.backend_donaciones.domain.repository.DonationRepository;
import com.proy.backend_donaciones.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    @Autowired private DonationRepository donationRepository;
    @Autowired private UserRepository userRepository;

    public Donation createDonation(CreateDonationRequest request) {
        // Obtenemos el email del usuario autenticado (¡Seguridad!)
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User donor = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("Usuario no autenticado correctamente."));

        // Creamos el objeto de dominio a partir del DTO
        Donation newDonation = new Donation();
        newDonation.setFoodCategory(request.getFoodCategory());
        newDonation.setDescription(request.getDescription());
        newDonation.setApproximateQuantity(request.getApproximateQuantity());
        newDonation.setUnit(request.getUnit());
        newDonation.setPickupAddress(request.getPickupAddress());
        newDonation.setPickupDate(request.getPickupDate());
        newDonation.setPickupTime(request.getPickupTime());
        newDonation.setContactPhone(request.getContactPhone());
        newDonation.setAssignmentType(request.getAssignmentType());
        newDonation.setBeneficiaryId(request.getBeneficiaryId());
        newDonation.setBeneficiaryType(request.getBeneficiaryType());
        newDonation.setConsumable(request.isConsumable());

        // Asignamos los datos del sistema
        newDonation.setDonorId(donor.getUserId());
        newDonation.setCreationDate(LocalDateTime.now());
        newDonation.setStatus("PENDIENTE");

        return donationRepository.save(newDonation);
    }

    public List<Donation> findDonationsByCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User donor = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("Usuario no autenticado."));
        return donationRepository.findByDonorId(donor.getUserId());
    }
}