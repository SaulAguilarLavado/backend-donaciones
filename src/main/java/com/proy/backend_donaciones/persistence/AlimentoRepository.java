package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Food;
import com.proy.backend_donaciones.domain.repository.FoodRepository;
import com.proy.backend_donaciones.persistence.crud.AlimentoCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Alimento;
import com.proy.backend_donaciones.persistence.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlimentoRepository implements FoodRepository {

    @Autowired
    private AlimentoCrudRepository alimentoCrudRepository;
    @Autowired
    private FoodMapper mapper;

    @Override
    public List<Food> getAll() {
        List<Alimento> alimentos = alimentoCrudRepository.findAll();
        return mapper.toFoods(alimentos);
    }

    @Override
    public Optional<Food> findById(long id) {
        return alimentoCrudRepository.findById(id).map(mapper::toFood);
    }

    @Override
    public Food save(Food food) {
        Alimento alimento = mapper.toAlimento(food);
        return mapper.toFood(alimentoCrudRepository.save(alimento));
    }

    @Override
    public void delete(long id) {
        alimentoCrudRepository.deleteById(id);
    }

    @Override
    public List<Food> findLatest3() {
        List<Alimento> alimentos = alimentoCrudRepository.findFirst3ByOrderByIdDesc();
        return mapper.toFoods(alimentos);
    }
}