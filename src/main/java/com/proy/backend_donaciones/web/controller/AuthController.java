package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.dto.AuthResponse;
import com.proy.backend_donaciones.domain.dto.LoginRequest;
import com.proy.backend_donaciones.domain.dto.RegisterPersonRequest;
import com.proy.backend_donaciones.domain.dto.RegisterRestaurantRequest;
import com.proy.backend_donaciones.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register/person")
    public ResponseEntity<String> registerPerson(@RequestBody RegisterPersonRequest request) {
        try {
            authService.registerPerson(request);
            return new ResponseEntity<>("Persona registrada exitosamente!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<String> registerRestaurant(@RequestBody RegisterRestaurantRequest request) {
        try {
            authService.registerRestaurant(request);
            return new ResponseEntity<>("Restaurante registrado exitosamente!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return new ResponseEntity("Email o contraseña inválidos", HttpStatus.UNAUTHORIZED);
        }
    }
}
