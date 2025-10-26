package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastname")
    @Mapping(source = "telefono", target = "phone")
    User toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}