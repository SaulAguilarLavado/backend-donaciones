package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.UserPoints;
import com.proy.backend_donaciones.domain.service.PuntoUsuarioService;
import com.proy.backend_donaciones.domain.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos")
public class PuntosController {

    private final PuntoUsuarioService service;
    private final UserRepository userRepository;

    public PuntosController(PuntoUsuarioService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping("/mis")
    public List<UserPoints> misPuntos() {

        // Obtener email autenticado desde el JWT
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Obtener ID del usuario asociado al email
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                .getUserId();

        // Retornar historial
        return service.verMisPuntos(userId);
    }

    @GetMapping("/ranking")
    public List<UserPoints> ranking() {
        return service.ranking();
    }
}
