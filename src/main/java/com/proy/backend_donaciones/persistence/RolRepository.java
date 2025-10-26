package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.Role;
import com.proy.backend_donaciones.domain.repository.RoleRepository;
import com.proy.backend_donaciones.persistence.crud.RolCrudRepository;
import com.proy.backend_donaciones.persistence.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class RolRepository implements RoleRepository {

    @Autowired
    private RolCrudRepository rolCrudRepository;
    @Autowired
    private RoleMapper mapper;

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return rolCrudRepository.findByNombre(roleName).map(mapper::toRole);
    }
}
