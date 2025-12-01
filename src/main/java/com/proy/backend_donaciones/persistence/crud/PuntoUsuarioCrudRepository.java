package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.PuntoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntoUsuarioCrudRepository extends JpaRepository<PuntoUsuario, Long> {

    List<PuntoUsuario> findByUsuarioIdOrderByFechaRegistroDesc(Long usuarioId);

    // Para ranking
    List<PuntoUsuario> findAll();
}
