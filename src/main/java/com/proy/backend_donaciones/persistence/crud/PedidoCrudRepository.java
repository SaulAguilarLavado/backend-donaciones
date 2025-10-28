package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoCrudRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDonanteId(Long donanteId);
}