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

    // Endpoint para que cualquier usuario autenticado vea la lista
    @GetMapping
    public ResponseEntity<List<Ngo>> getAll() {
        return ResponseEntity.ok(ngoService.getAll());
    }

    // Endpoint para que SOLO el ADMIN pueda crear una nueva ONG
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ngo> save(@RequestBody Ngo ngo) {
        return new ResponseEntity<>(ngoService.save(ngo), HttpStatus.CREATED);
    }

    // Endpoint para que SOLO el ADMIN pueda eliminar una ONG
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