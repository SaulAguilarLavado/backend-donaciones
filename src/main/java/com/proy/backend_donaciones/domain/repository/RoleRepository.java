package com.proy.backend_donaciones.domain.repository;

import com.proy.backend_donaciones.domain.Role;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByRoleName(String roleName);
}
