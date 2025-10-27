package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Ngo;
import com.proy.backend_donaciones.persistence.entity.Ong;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NgoMapper {
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "urlImagen", target = "imageUrl")
    Ngo toNgo(Ong ong);
    List<Ngo> toNgos(List<Ong> ongs);

    @InheritInverseConfiguration
    Ong toOng(Ngo ngo);
}