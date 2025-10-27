package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Shelter;
import com.proy.backend_donaciones.persistence.entity.Albergue;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "urlImagen", target = "imageUrl")
    Shelter toShelter(Albergue albergue);
    List<Shelter> toShelters(List<Albergue> albergues);

    @InheritInverseConfiguration
    Albergue toAlbergue(Shelter shelter);
}