package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.UserPoints;
import com.proy.backend_donaciones.domain.dto.RankingDTO;
import com.proy.backend_donaciones.domain.repository.PuntoUsuarioDomainRepository;
import com.proy.backend_donaciones.persistence.crud.PuntoUsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.PuntoUsuario;
import com.proy.backend_donaciones.persistence.mapper.PuntoUsuarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PuntoUsuarioRepository implements PuntoUsuarioDomainRepository {

    @Autowired
    private PuntoUsuarioCrudRepository crud;

    @Autowired
    private UsuarioCrudRepository usuarioCrud;

    @Autowired
    private PuntoUsuarioMapper mapper;

    @Override
    public UserPoints save(Long userId, int puntos, String motivo) {
        PuntoUsuario entity = new PuntoUsuario();
        entity.setPuntos(puntos);
        entity.setMotivo(motivo);

        usuarioCrud.findById(userId).ifPresent(entity::setUsuario);

        return mapper.toUserPoints(crud.save(entity));
    }

    @Override
    public List<UserPoints> getPointsByUser(Long userId) {
        return mapper.toUserPointsList(
                crud.findByUsuarioIdOrderByFechaRegistroDesc(userId)
        );
    }

    @Override
    public List<RankingDTO> getRanking() {
        return crud.obtenerRanking();
    }
}
