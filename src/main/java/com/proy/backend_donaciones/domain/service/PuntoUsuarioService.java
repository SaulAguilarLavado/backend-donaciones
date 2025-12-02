package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.UserPoints;
import com.proy.backend_donaciones.domain.dto.RankingDTO;
import com.proy.backend_donaciones.domain.repository.PuntoUsuarioDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntoUsuarioService {

    @Autowired
    private PuntoUsuarioDomainRepository repo;

    public void asignarPuntos(Long userId, int puntos, String motivo) {
        repo.save(userId, puntos, motivo);
    }

    public List<UserPoints> verMisPuntos(Long userId) {
        return repo.getPointsByUser(userId);
    }

   public List<RankingDTO> ranking() {
    return repo.getRanking();
}
}
