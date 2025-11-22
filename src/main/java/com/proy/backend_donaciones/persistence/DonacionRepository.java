package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Donation;
import com.proy.backend_donaciones.domain.repository.DonationRepository;
import com.proy.backend_donaciones.persistence.crud.DonacionCrudRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Donacion;
import com.proy.backend_donaciones.persistence.mapper.DonationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DonacionRepository implements DonationRepository {

    @Autowired private DonacionCrudRepository donacionCrudRepository;
    @Autowired private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired private DonationMapper mapper;

    // --- MÉTODOS QUE YA TENÍAS ---
    @Override
    public List<Donation> findByDonorId(Long donorId) {
        return mapper.toDonations(donacionCrudRepository.findByDonanteIdOrderByFechaCreacionDesc(donorId));
    }

    @Override
    public Donation save(Donation donation) {
        Donacion donacionEntity = mapper.toDonacion(donation);
        usuarioCrudRepository.findById(donation.getDonorId()).ifPresent(donacionEntity::setDonante);
        return mapper.toDonation(donacionCrudRepository.save(donacionEntity));
    }

    // --- NUEVAS IMPLEMENTACIONES ---
    @Override
    public List<Donation> getAll() {
        return mapper.toDonations(donacionCrudRepository.findAll());
    }

    @Override
    public Optional<Donation> findById(Long id) {
        return donacionCrudRepository.findById(id).map(mapper::toDonation);
    }

    @Override
    public void delete(Long id) {
        donacionCrudRepository.deleteById(id);
    }
    @Override
public List<Donation> findByUsuarioEmail(String email) {
    return mapper.toDonations(
        donacionCrudRepository.findByDonanteEmailOrderByFechaCreacionDesc(email)
    );
}
}