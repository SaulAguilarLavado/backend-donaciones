package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonacionCrudRepository extends JpaRepository<Donacion, Long> {
    // Para la página "Estado de Pedido" del usuario
    List<Donacion> findByDonanteIdOrderByFechaCreacionDesc(Long donanteId);
}