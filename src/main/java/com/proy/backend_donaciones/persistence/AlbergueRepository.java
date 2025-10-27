package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Shelter;
import com.proy.backend_donaciones.domain.repository.ShelterRepository;
import com.proy.backend_donaciones.persistence.crud.AlbergueCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Albergue;
import com.proy.backend_donaciones.persistence.mapper.ShelterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbergueRepository implements ShelterRepository {

    @Autowired
    private AlbergueCrudRepository albergueCrudRepository;
    @Autowired
    private ShelterMapper mapper;

    @Override
    public List<Shelter> getAll() {
        return mapper.toShelters(albergueCrudRepository.findAll());
    }

    @Override
    public Optional<Shelter> findById(long id) {
        return albergueCrudRepository.findById(id).map(mapper::toShelter);
    }

    @Override
    public Shelter save(Shelter shelter) {
        Albergue albergue = mapper.toAlbergue(shelter);
        return mapper.toShelter(albergueCrudRepository.save(albergue));
    }

    @Override
    public void delete(long id) {
        albergueCrudRepository.deleteById(id);
    }

    @Override
    public List<Shelter> findLatest3() {
        return mapper.toShelters(albergueCrudRepository.findFirst3ByOrderByIdDesc());
    }
}