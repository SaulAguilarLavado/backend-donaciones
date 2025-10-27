package com.proy.backend_donaciones.web.controller;

import com.proy.backend_donaciones.domain.Food;
import com.proy.backend_donaciones.domain.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    @PreAuthorize("isAuthenticated()") // Todos los logueados pueden ver la lista
    public ResponseEntity<List<Food>> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Solo el admin puede crear
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return new ResponseEntity<>(foodService.save(food), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Solo el admin puede borrar
    public ResponseEntity<Void> delete(@PathVariable("id") long foodId) {
        if (foodService.delete(foodId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}