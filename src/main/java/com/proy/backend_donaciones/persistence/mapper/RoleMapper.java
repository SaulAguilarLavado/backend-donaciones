package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.Role;
import com.proy.backend_donaciones.persistence.entity.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "id", target = "roleId")
    @Mapping(source = "nombre", target = "roleName")
    Role toRole(Rol rol);

    @InheritInverseConfiguration
    Rol toRol(Role role);
}
