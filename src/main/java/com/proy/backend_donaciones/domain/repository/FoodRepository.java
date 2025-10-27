package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Food;
import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    List<Food> getAll();
    Optional<Food> findById(long id);
    Food save(Food food);
    void delete(long id);

    // Método específico para el dashboard
    List<Food> findLatest3();
}