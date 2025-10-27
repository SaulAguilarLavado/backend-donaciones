package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Ngo;
import com.proy.backend_donaciones.domain.repository.NgoRepository;
import com.proy.backend_donaciones.persistence.crud.OngCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Ong;
import com.proy.backend_donaciones.persistence.mapper.NgoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OngRepository implements NgoRepository {

    @Autowired
    private OngCrudRepository ongCrudRepository;
    @Autowired
    private NgoMapper mapper;

    @Override
    public List<Ngo> getAll() {
        return mapper.toNgos(ongCrudRepository.findAll());
    }

    @Override
    public Optional<Ngo> findById(long id) {
        return ongCrudRepository.findById(id).map(mapper::toNgo);
    }

    @Override
    public Ngo save(Ngo ngo) {
        Ong ong = mapper.toOng(ngo);
        return mapper.toNgo(ongCrudRepository.save(ong));
    }

    @Override
    public void delete(long id) {
        ongCrudRepository.deleteById(id);
    }

    @Override
    public List<Ngo> findLatest3() {
        return mapper.toNgos(ongCrudRepository.findFirst3ByOrderByIdDesc());
    }
}