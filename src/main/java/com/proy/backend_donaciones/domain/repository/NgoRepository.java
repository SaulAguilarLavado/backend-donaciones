package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Ngo;
import java.util.List;
import java.util.Optional;

public interface NgoRepository {
    List<Ngo> getAll();
    Optional<Ngo> findById(long id);
    Ngo save(Ngo ngo);
    void delete(long id);

    // Método específico para el dashboard
    List<Ngo> findLatest3();
}