package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.domain.dto.RankingDTO;
import com.proy.backend_donaciones.persistence.entity.PuntoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PuntoUsuarioCrudRepository extends JpaRepository<PuntoUsuario, Long> {

    List<PuntoUsuario> findByUsuarioIdOrderByFechaRegistroDesc(Long usuarioId);

    // Para ranking
    List<PuntoUsuario> findAll();
    @Query("""
    SELECT new com.proy.backend_donaciones.domain.dto.RankingDTO(
        u.nombre,
        SUM(p.puntos)
    )
    FROM PuntoUsuario p
    JOIN p.usuario u
    GROUP BY u.id, u.nombre
    ORDER BY SUM(p.puntos) DESC
""")
List<RankingDTO> obtenerRanking();

}
