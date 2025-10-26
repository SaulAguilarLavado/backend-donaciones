package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    boolean existsByRuc(String ruc);
}
