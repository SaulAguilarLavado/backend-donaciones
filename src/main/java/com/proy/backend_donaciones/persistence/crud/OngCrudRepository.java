package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OngCrudRepository extends JpaRepository<Ong, Long> {
    List<Ong> findFirst3ByOrderByIdDesc();
}