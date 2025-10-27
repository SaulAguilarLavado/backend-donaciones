package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Shelter;
import com.proy.backend_donaciones.domain.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public List<Shelter> getAll() {
        return shelterRepository.getAll();
    }

    public Optional<Shelter> findById(long id) {
        return shelterRepository.findById(id);
    }

    public Shelter save(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    /**
     * Elimina un albergue si existe.
     * @param id El ID del albergue a eliminar.
     * @return true si el albergue fue eliminado, false si no se encontró.
     */
    public boolean delete(long id) {
        return findById(id).map(shelter -> {
            shelterRepository.delete(id);
            return true;
        }).orElse(false);
    }
}