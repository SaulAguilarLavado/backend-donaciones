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

    // Obtener todas las donaciones
    @Override
    public List<Donation> getAll() {
        return mapper.toDonations(donacionCrudRepository.findAll());
    }

    // Buscar donación por ID (entity -> domain)
    @Override
    public Optional<Donation> findById(Long id) {
        return donacionCrudRepository.findById(id)
                .map(mapper::toDonation);
    }

    // Guardar donación (domain -> entity -> domain)
    @Override
    public Donation save(Donation donation) {
        Donacion entity = mapper.toDonacion(donation);

        // Setear donante (relación ManyToOne)
        usuarioCrudRepository.findById(donation.getDonorId())
                .ifPresent(entity::setDonante);

        return mapper.toDonation(donacionCrudRepository.save(entity));
    }

    // Eliminar donación
    @Override
    public void delete(Long id) {
        donacionCrudRepository.deleteById(id);
    }

    // Buscar por ID de donante
    @Override
    public List<Donation> findByDonorId(Long donorId) {
        return mapper.toDonations(
                donacionCrudRepository.findByDonanteIdOrderByFechaCreacionDesc(donorId)
        );
    }

    // Buscar por email de donante
    @Override
    public List<Donation> findByUsuarioEmail(String email) {
        return mapper.toDonations(
                donacionCrudRepository.findByDonanteEmailOrderByFechaCreacionDesc(email)
        );
    }

    // ****** MÉTODOS ESPECIALES PARA ENTITY  ******

    public Optional<Donacion> findEntityById(Long id) {
        return donacionCrudRepository.findById(id);
    }

    public Donacion saveEntity(Donacion donacion) {
        return donacionCrudRepository.save(donacion);
    }

      public List<Donation> findByBeneficiaryId(Long beneficiaryId) {
        return mapper.toDonations(donacionCrudRepository.findByIdBeneficiarioOrderByFechaCreacionDesc(beneficiaryId));
    }
}
