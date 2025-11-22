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
import java.util.Optional;

@Service
public class DonationService {

    @Autowired private DonationRepository donationRepository;
    @Autowired private UserRepository userRepository;

    public List<Donation> getAll() {
        return donationRepository.getAll();
    }

    public Optional<Donation> getById(Long id) {
        return donationRepository.findById(id);
    }

    public Donation save(Donation donation) {
        // Obtener donante desde JWT
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
