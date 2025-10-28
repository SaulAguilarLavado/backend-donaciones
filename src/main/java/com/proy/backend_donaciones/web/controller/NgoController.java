package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.Ngo;
import com.proy.backend_donaciones.domain.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ngos")
public class NgoController {

    @Autowired
    private NgoService ngoService;

    // --- ¡AQUÍ ESTÁ LA CORRECCIÓN! ---
    @GetMapping
    @PreAuthorize("isAuthenticated()") // Añadimos esta línea
    public ResponseEntity<List<Ngo>> getAll() {
        return ResponseEntity.ok(ngoService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ngo> save(@RequestBody Ngo ngo) {
        return new ResponseEntity<>(ngoService.save(ngo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long ngoId) {
        if (ngoService.delete(ngoId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}