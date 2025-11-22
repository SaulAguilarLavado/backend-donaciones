package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Donation;
import java.util.List;
import java.util.Optional;

public interface DonationRepository {
    Donation save(Donation donation);
    Optional<Donation> findById(Long id);
    List<Donation> getAll();
    void delete(Long id);

    // Método adicional para filtrar por donante
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByUsuarioEmail(String email);
}
