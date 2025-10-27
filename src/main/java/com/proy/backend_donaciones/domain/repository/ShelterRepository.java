package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Shelter;
import java.util.List;
import java.util.Optional;

public interface ShelterRepository {
    List<Shelter> getAll();
    Optional<Shelter> findById(long id);
    Shelter save(Shelter shelter);
    void delete(long id);

    // Método específico para el dashboard
    List<Shelter> findLatest3();
}