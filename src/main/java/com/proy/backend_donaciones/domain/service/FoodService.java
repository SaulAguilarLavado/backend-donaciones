package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Food;
import com.proy.backend_donaciones.domain.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAll() {
        return foodRepository.getAll();
    }

    public Optional<Food> findById(long id) {
        return foodRepository.findById(id);
    }

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    /**
     * Elimina un alimento si existe.
     * @param id El ID del alimento a eliminar.
     * @return true si el alimento fue eliminado, false si no se encontró.
     */
    public boolean delete(long id) {
        return findById(id).map(food -> {
            foodRepository.delete(id);
            return true;
        }).orElse(false);
    }
}