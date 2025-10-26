package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolCrudRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombre(String nombre);
}
