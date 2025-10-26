package com.proy.backend_donaciones.persistence.crud;

import com.proy.backend_donaciones.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByDni(String dni);
    Boolean existsByRuc(String ruc);
}
