package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Ngo;
import com.proy.backend_donaciones.domain.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NgoService {

    @Autowired
    private NgoRepository ngoRepository;

    public List<Ngo> getAll() {
        return ngoRepository.getAll();
    }

    public Optional<Ngo> findById(long id) {
        return ngoRepository.findById(id);
    }

    public Ngo save(Ngo ngo) {
        return ngoRepository.save(ngo);
    }

    /**
     * Elimina una ONG si existe.
     * @param id El ID de la ONG a eliminar.
     * @return true si la ONG fue eliminada, false si no se encontró.
     */
    public boolean delete(long id) {
        return findById(id).map(ngo -> {
            ngoRepository.delete(id);
            return true;
        }).orElse(false);
    }
}