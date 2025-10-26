package com.proy.backend_donaciones.persistence;

import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.repository.UserRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Usuario;
import com.proy.backend_donaciones.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public User save(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioCrudRepository.findByEmail(email).map(mapper::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioCrudRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDni(String dni) {
        return usuarioCrudRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByRuc(String ruc) {
        return usuarioCrudRepository.existsByRuc(ruc);
    }
}
