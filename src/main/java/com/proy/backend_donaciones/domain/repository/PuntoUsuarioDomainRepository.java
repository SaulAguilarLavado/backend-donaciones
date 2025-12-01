package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.UserPoints;
import java.util.List;

public interface PuntoUsuarioDomainRepository {

    UserPoints save(Long userId, int puntos, String motivo);

    List<UserPoints> getPointsByUser(Long userId);

    List<UserPoints> getAll(); // para ranking
}
