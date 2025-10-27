package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Albergue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlbergueCrudRepository extends JpaRepository<Albergue, Long> {
    List<Albergue> findFirst3ByOrderByIdDesc();
}