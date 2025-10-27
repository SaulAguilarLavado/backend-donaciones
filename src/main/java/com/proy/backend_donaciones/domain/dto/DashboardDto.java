package com.proy.backend_donaciones.domain.dto;

import com.proy.backend_donaciones.domain.Food; // Asumiendo que has creado Food.java
import com.proy.backend_donaciones.domain.Shelter; // Asumiendo que has creado Shelter.java
import com.proy.backend_donaciones.domain.Ngo; // Asumiendo que has creado Ngo.java
import java.util.List;

public class DashboardDto {
    private List<Food> latestFoods;
    private List<Shelter> latestShelters;
    private List<Ngo> latestNgos;

    //<editor-fold desc="Getters and Setters">
    public List<Food> getLatestFoods() {
        return latestFoods;
    }

    public void setLatestFoods(List<Food> latestFoods) {
        this.latestFoods = latestFoods;
    }

    public List<Shelter> getLatestShelters() {
        return latestShelters;
    }

    public void setLatestShelters(List<Shelter> latestShelters) {
        this.latestShelters = latestShelters;
    }

    public List<Ngo> getLatestNgos() {
        return latestNgos;
    }

    public void setLatestNgos(List<Ngo> latestNgos) {
        this.latestNgos = latestNgos;
    }
    //</editor-fold>
}