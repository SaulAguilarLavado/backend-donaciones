package com.proy.backend_donaciones.domain.service;

import com.proy.backend_donaciones.domain.Role;
import com.proy.backend_donaciones.domain.User;
import com.proy.backend_donaciones.domain.dto.LoginRequest;
import com.proy.backend_donaciones.domain.dto.RegisterPersonRequest;
import com.proy.backend_donaciones.domain.dto.RegisterRestaurantRequest;
import com.proy.backend_donaciones.domain.repository.RoleRepository;
import com.proy.backend_donaciones.domain.repository.UserRepository;
import com.proy.backend_donaciones.web.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenProvider jwtTokenProvider;

    public User registerPerson(RegisterPersonRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso.");
        }
        if (userRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDni(request.getDni());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // --- CAMBIO CLAVE: Usamos un nombre de rol específico ---
        Role userRole = roleRepository.findByRoleName("DONANTE_PERSONA")
                .orElseThrow(() -> new RuntimeException("Rol 'DONANTE_PERSONA' no encontrado."));

        user.setRoles(Set.of(userRole));
        return userRepository.save(user);
    }

    public User registerRestaurant(RegisterRestaurantRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso.");
        }
        if (userRepository.existsByRuc(request.getRuc())) {
            throw new RuntimeException("El RUC ya está registrado.");
        }

        User user = new User();
        user.setName(request.getRestaurantName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRuc(request.getRuc());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // --- CAMBIO CLAVE: Usamos un nombre de rol específico ---
        Role userRole = roleRepository.findByRoleName("DONANTE_RESTAURANTE")
                .orElseThrow(() -> new RuntimeException("Rol 'DONANTE_RESTAURANTE' no encontrado."));

        user.setRoles(Set.of(userRole));
        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}
