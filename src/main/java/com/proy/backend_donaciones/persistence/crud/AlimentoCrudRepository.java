package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlimentoCrudRepository extends JpaRepository<Alimento, Long> {
    // Spring Data JPA crea la consulta para obtener los 3 últimos por ID descendente
    List<Alimento> findFirst3ByOrderByIdDesc();
}