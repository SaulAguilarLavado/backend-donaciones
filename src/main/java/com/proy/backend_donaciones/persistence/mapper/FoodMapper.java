package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Food;
import com.proy.backend_donaciones.persistence.entity.Alimento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "urlImagen", target = "imageUrl")
    Food toFood(Alimento alimento);
    List<Food> toFoods(List<Alimento> alimentos);

    @InheritInverseConfiguration
    Alimento toAlimento(Food food);
}