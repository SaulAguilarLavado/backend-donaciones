package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.Shelter;
import com.proy.backend_donaciones.domain.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    // Endpoint para que cualquier usuario autenticado vea la lista
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Shelter>> getAll() {
        return ResponseEntity.ok(shelterService.getAll());
    }

    // Endpoint para que SOLO el ADMIN pueda crear un nuevo Albergue
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shelter> save(@RequestBody Shelter shelter) {
        return new ResponseEntity<>(shelterService.save(shelter), HttpStatus.CREATED);
    }

    // Endpoint para que SOLO el ADMIN pueda eliminar un Albergue
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") long shelterId) {
        if (shelterService.delete(shelterId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}