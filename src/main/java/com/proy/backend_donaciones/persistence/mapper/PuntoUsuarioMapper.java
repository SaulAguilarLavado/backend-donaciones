package com.proy.backend_donaciones.persistence.mapper;

import com.proy.backend_donaciones.domain.UserPoints;
import com.proy.backend_donaciones.persistence.entity.PuntoUsuario;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntoUsuarioMapper {

    @Mappings({
        @Mapping(source = "id", target = "pointId"),
        @Mapping(source = "usuario.id", target = "userId"),
        @Mapping(source = "puntos", target = "points"),
        @Mapping(source = "motivo", target = "reason"),
        @Mapping(source = "fechaRegistro", target = "dateAssigned")
    })
    UserPoints toUserPoints(PuntoUsuario puntos);

    List<UserPoints> toUserPointsList(List<PuntoUsuario> lista);

}
